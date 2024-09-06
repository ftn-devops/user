package ftn.devops.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.devops.user.entity.Role;
import ftn.devops.user.entity.RoleType;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleType(RoleType roleType); 
}
