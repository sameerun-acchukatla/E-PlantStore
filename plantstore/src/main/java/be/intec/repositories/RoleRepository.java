package be.intec.repositories;

import be.intec.models.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByname(String name);
}
