package se.team_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.team_management.models.TaskAssignment;

public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, Integer> {
}
