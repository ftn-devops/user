package ftn.devops.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.devops.user.entity.Role;
import ftn.devops.user.entity.RoleType;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRoleType(RoleType roleType); 
}
