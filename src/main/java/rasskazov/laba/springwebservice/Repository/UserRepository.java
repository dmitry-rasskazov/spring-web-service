package rasskazov.laba.springwebservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rasskazov.laba.springwebservice.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByEmail(String email);
}
