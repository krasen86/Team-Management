package se.team_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.team_management.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository <Employee, Integer> {
    Optional<Employee> findByUsername(String username);
    Optional<Employee> findByEmail(String email);
    Optional<List<Employee>> findAllByFirstName(String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
