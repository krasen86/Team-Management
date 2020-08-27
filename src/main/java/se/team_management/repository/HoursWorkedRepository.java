package se.team_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.team_management.models.HoursWorked;

public interface HoursWorkedRepository extends JpaRepository<HoursWorked, Integer> {
}
