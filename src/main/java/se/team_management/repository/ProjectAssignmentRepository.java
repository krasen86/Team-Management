package se.team_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.team_management.models.ProjectAssignment;

import java.util.List;
import java.util.Optional;

public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, Integer> {

    Optional<List<ProjectAssignment>> findAllByEmployee_Id(Integer employeeID);
    Optional<List<ProjectAssignment>> findAllByProjectId(Integer projectID);
    boolean existsByEmployeeIdAndProjectId(Integer employee_id, Integer project_id);
}
