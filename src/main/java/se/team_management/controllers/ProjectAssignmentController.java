package se.team_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.team_management.models.Project;
import se.team_management.models.ProjectAssignment;
import se.team_management.servises.ProjectAssignmentDAO;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/assignments")
public class ProjectAssignmentController {


    @Autowired
    private ProjectAssignmentDAO projectAssignmentDAO;



    @GetMapping("/employee/{id}")
    public ResponseEntity<?>  getAssignmentsByEmployeeID(@PathVariable(value = "id") Integer employeeID){
        return ResponseEntity.ok().body(projectAssignmentDAO.findByEmployeeId(employeeID));
    }
    @GetMapping("/project/{id}")
    public ResponseEntity<?>  getAssignmentsByProjectID(@PathVariable(value = "id") Integer projectID){
        return ResponseEntity.ok().body(projectAssignmentDAO.findAllByProjectId(projectID));
    }

    @GetMapping("/project/employee/{id}")
    public ResponseEntity<?>  getProjectsForIndividualEmployee(@PathVariable(value = "id") Integer employeeID){
        List<ProjectAssignment> projectAssignments = projectAssignmentDAO.findByEmployeeId(employeeID);
        List<Project> projects = new ArrayList<>();
        if (projectAssignments == null){
            return ResponseEntity
                    .badRequest()
                    .body("The employee doesn't work on any projects!");
        }
        for (int i = 0; i<projectAssignments.size(); i++){
            projects.add(projectAssignments.get(i).getProject());
        }
        return ResponseEntity.ok().body(projects);
    }

    @PostMapping()
    public ResponseEntity<?> createAssignment(@RequestBody ProjectAssignment projectAssignment){
        if (projectAssignmentDAO.findIfExistsByEmployeeIdAndProjectId(projectAssignment.getEmployee().getId(),projectAssignment.getProject().getId())) {
            return ResponseEntity
                    .badRequest()
                    .body("Assignment already exists!");
        }
        ProjectAssignment projectAssignmentNew;
        projectAssignmentNew = new ProjectAssignment(projectAssignment.getEmployee(), projectAssignment.getProject());

        return  ResponseEntity.ok().body(projectAssignmentDAO.save(projectAssignmentNew));
    }
}
