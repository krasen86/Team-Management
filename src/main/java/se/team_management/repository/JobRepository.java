package se.team_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.team_management.models.Job;

public interface JobRepository extends JpaRepository<Job, Integer> {
}
