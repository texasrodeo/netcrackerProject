package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


    User findByActivationCode(String code);
}
