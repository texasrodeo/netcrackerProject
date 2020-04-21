package com.netcrackerTask.backend.webrest;

import java.util.*;
import java.util.stream.Collectors;
import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.persistence.RoleRepository;
import com.netcrackerTask.backend.business.implementations.UserDetailsImpl;
import com.netcrackerTask.backend.jwt.JwtUtils;
import com.netcrackerTask.backend.business.implementations.UserServiceImpl;
import com.netcrackerTask.backend.business.payloads.JwtResponse;
import com.netcrackerTask.backend.business.payloads.LoginRequest;
import com.netcrackerTask.backend.business.payloads.MessageResponse;
import com.netcrackerTask.backend.business.payloads.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    AuthenticationManager authenticationManager;

    UserServiceImpl userService;

    RoleRepository roleRepository;

    PasswordEncoder encoder;

    JwtUtils jwtUtils;

    /**
     * Constructor
     *@param jwtUtils service associated with jwt
     * @param passwordEncoder bean that encodes password
     * @param userService service associated with users
     *@param authenticationManager service associated with authentication
     *
     */
    @Autowired
    public AuthenticationController(final UserServiceImpl userService, final JwtUtils jwtUtils,
                                    final PasswordEncoder passwordEncoder,
                                    final AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.encoder = passwordEncoder;
        this.userService = userService;
    }

    /**
     * Logs user in
     * @param loginRequest user credentials
     * @return jwt token
     * */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    /**
     * Logs user in
     * @param signUpRequest user credentials
     * @return response about user registration
     * */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Это имя пользователя уже занято"));
        }
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Этот e-mail уже занят"));
        }
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        return userService.saveUser(user, strRoles);
    }

    /**
     * Activates user email
     * @param code code in email set to user
     * @return jwt token
     * */
    @GetMapping("/activate/{code}")
    public Map<String, Boolean> activate(@PathVariable String code) {
        Map<String,Boolean> result = new HashMap<>();
        if(userService.activateUser(code)){
            result.put("activated", true);
        }
        else {
            result.put("activated", false);
        }
        return result;
    }



}
