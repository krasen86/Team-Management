package se.team_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.team_management.models.Employee;
import se.team_management.models.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findByTitle(String title);
    boolean existsByTitle(String title);
}
