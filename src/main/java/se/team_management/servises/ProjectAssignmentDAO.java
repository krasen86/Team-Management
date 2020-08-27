package se.team_management.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.team_management.models.ProjectAssignment;
import se.team_management.repository.ProjectAssignmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectAssignmentDAO {
    @Autowired
    private ProjectAssignmentRepository projectAssignmentRepository;

    public ProjectAssignment save(ProjectAssignment projectAssignment){
        return projectAssignmentRepository.save(projectAssignment);
    }

    public List<ProjectAssignment> findAll(){
        return projectAssignmentRepository.findAll();
    }

    public List<ProjectAssignment> findByEmployeeId(Integer employeeID){
        Optional<List<ProjectAssignment>> projectAssignments = projectAssignmentRepository.findAllByEmployee_Id(employeeID);
        projectAssignments.orElseThrow(() -> new UsernameNotFoundException("Assignments not found for ID: " + employeeID));
        return new ArrayList<>(projectAssignments.get());
    }

    public List<ProjectAssignment> findAllByProjectId(Integer projectID){
        Optional<List<ProjectAssignment>> projectAssignments = projectAssignmentRepository.findAllByProjectId(projectID);
        projectAssignments.orElseThrow(() -> new UsernameNotFoundException("Assignments not found for ID: " + projectID));
        return new ArrayList<>(projectAssignments.get());
    }

    public void delete(ProjectAssignment projectAssignment){
        projectAssignmentRepository.delete(projectAssignment);
    }

    public boolean findIfExistsByEmployeeIdAndProjectId(Integer employee_id, Integer project_id){
        return projectAssignmentRepository.existsByEmployeeIdAndProjectId(employee_id,project_id);
    }
}
