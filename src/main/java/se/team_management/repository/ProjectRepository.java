package se.team_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.team_management.models.Employee;
import se.team_management.models.Project;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Project findProjectByName(String name);
    boolean existsByName(String title);

}
