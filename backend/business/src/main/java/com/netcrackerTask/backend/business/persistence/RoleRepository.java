package com.netcrackerTask.backend.business.persistence;

import com.netcrackerTask.backend.business.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
