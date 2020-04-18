package com.netcrackerTask.backend.business.entity;

import javax.persistence.*;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * Role entity.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role implements GrantedAuthority {

    /**
     * Role id.
     */
    @Id
    private Long id;

    /**
     * Role name.
     */
    private String name;

    /**
     * Users who have this role.
     */
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return getName();
    }

}
