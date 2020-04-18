package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Gets user
     * @param username user name
     * @return user
     * */
    User findByUsername(String username);

    /**
     * Gets user
     * @param id user id
     * @return user
     * */
    User getById(Long id);

    /**
     * Checks if there is a user in database with such user name
     * @param username user user name
     * @return boolean value of operation status
     * */
    Boolean existsByUsername(String username);

    /**
     * Checks if there is a user in database with such email
     * @param email user email
     * @return boolean value of operation status
     * */
    Boolean existsByEmail(String email);

    /**
     * Gets user
     * @param code user activation code
     * @return user
     * */
    User findByActivationCode(String code);
}
