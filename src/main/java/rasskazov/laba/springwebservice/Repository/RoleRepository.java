package rasskazov.laba.springwebservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rasskazov.laba.springwebservice.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    Role findByName(String name);
}
