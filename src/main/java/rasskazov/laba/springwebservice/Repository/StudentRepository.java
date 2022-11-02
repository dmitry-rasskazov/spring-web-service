package rasskazov.laba.springwebservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rasskazov.laba.springwebservice.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{
}
