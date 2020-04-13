package com.netcrackerTask.backend.webrest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.payloads.JwtResponse;
import com.netcrackerTask.backend.business.payloads.LoginRequest;
import com.netcrackerTask.backend.business.payloads.MessageResponse;
import com.netcrackerTask.backend.business.payloads.SignupRequest;
import com.netcrackerTask.backend.business.persistence.RoleRepository;
import com.netcrackerTask.backend.business.service.implementations.UserDetailsImpl;
import com.netcrackerTask.backend.business.service.implementations.UserService;
import com.netcrackerTask.backend.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    AuthenticationManager authenticationManager;

    UserService userService;

    RoleRepository roleRepository;

    PasswordEncoder encoder;

    JwtUtils jwtUtils;

    private static final String BAD_USERNAME_RESPONSE = "Это имя пользователя уже занято";
    private static final String BAD_EMAIL_RESPONSE = "Этот e-mail уже занят";

    @Autowired
    public AuthenticationController(final UserService userService, final JwtUtils jwtUtils,
                                    final PasswordEncoder passwordEncoder, final RoleRepository roleRepository,
                                    final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.encoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

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

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse(BAD_USERNAME_RESPONSE));
        }
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse(BAD_EMAIL_RESPONSE));
        }
        User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        return userService.saveUser(user, strRoles);
    }

    @GetMapping("/activate/{code}")
    public Map<String, Boolean> activate(@PathVariable String code) {
        Map<String, Boolean> result = new HashMap<>();
        result.put("activated", userService.activateUser(code));
        return result;
    }


}
