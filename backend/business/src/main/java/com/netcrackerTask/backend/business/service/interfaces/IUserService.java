package com.netcrackerTask.backend.business.service.interfaces;

import com.netcrackerTask.backend.business.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

public interface IUserService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    ResponseEntity<?> saveUser(User user, Set<String> strRoles);

    User findByUsername(String name);

    boolean activateUser(String code);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean checkEmail(Long id);
}
