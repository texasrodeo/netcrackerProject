package com.netcrackerTask.backend.business.persistence;

import java.util.Optional;
import com.netcrackerTask.backend.business.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Gets role
     * @param name role name
     * @return role
     * */
    Optional<Role> findByName(String name);
}
