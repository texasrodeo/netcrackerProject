package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User getById(Long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findByActivationCode(String code);
}
