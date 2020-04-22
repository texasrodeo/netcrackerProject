package com.netcrackerTask.backend.business.implementations;

import com.netcrackerTask.backend.business.entity.Role;
import com.netcrackerTask.backend.business.entity.User;
import com.netcrackerTask.backend.business.payloads.MessageResponse;
import com.netcrackerTask.backend.business.persistence.RoleRepository;
import com.netcrackerTask.backend.business.persistence.UserRepository;
import com.netcrackerTask.backend.business.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    private final MailServiceImpl mailService;

    UserRepository userRepository;

    RoleRepository roleRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Constructor.
     * @param userRepository user DAO
     *@param roleRepository relos DAO
     * @param mailService service serving emailing
     * @param bCryptPasswordEncoder password encrypt
     * */
    @Autowired
    public UserServiceImpl(final MailServiceImpl mailService, final UserRepository userRepository, final RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.mailService = mailService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return UserDetailsImpl.build(user);
    }

    public ResponseEntity<?> saveUser(User user, Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("admin".equals(role)) {
                    Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName("ROLE_USER")
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);
        String href="http://localhost:4200/activate/"+user.getActivationCode();
        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format("Здравствуйте, "
                    +user.getUsername()+".  <b>Для активации Вашего аккаунта перейдите по </b>"
                    +"<a href=\"%s\">ссылке</a>",
                    href);
            mailService.send(user.getEmail(), "Код активации", message);
        }
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if(user==null){
            return false;
        }
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean checkEmail(Long id) {
        User user = userRepository.getById(id);
        return user.getActivationCode()==null;
    }
}
