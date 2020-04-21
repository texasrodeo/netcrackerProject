package com.netcrackerTask.backend.business.service.interfaces;

import java.util.Set;
import com.netcrackerTask.backend.business.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService {

    /**
     * Gets user
     * @param username user name
     * @throws UsernameNotFoundException if user is not found
     * @return user
     * */
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Saves user in database
     * @param user user
     * @param strRoles user roles
     * @return response about status of operetion
     * */
    ResponseEntity<?> saveUser(User user, Set<String> strRoles);

    /**
     * Gets user
     * @param username user name
     * @return user
     * */
    User findByUsername(String username);

    /**
     * Activates user account
     * @param code code
     * @return boolean value of operation status
     * */
    boolean activateUser(String code);

    /**
     * Checks if there is a user in database with such name
     * @param username user name
     * @return boolean value of operation status
     * */
    boolean existsByUsername(String username);

    /**
     * Checks if there is a user in database with such email
     * @param email user email
     * @return boolean value of operation status
     * */
    boolean existsByEmail(String email);

    /**
     * Checks if user has activated their account
     * @param id user id
     * @return boolean value of operation status
     * */
    boolean checkEmail(Long id);
}
