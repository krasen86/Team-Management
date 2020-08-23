package se.team_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.team_management.models.ProjectAssignment;
import se.team_management.servises.ProjectAssignmentDAO;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/assignments")
public class ProjectAssignmentController {


    @Autowired
    ProjectAssignmentDAO projectAssignmentDAO;



    @GetMapping("/employee/{id}")
    public ResponseEntity<?>  getAssignmentsByEmployeeID(@PathVariable(value = "id") Integer employeeID){
        return ResponseEntity.ok().body(projectAssignmentDAO.findByEmployeeId(employeeID));
    }
    @GetMapping("/project/{id}")
    public ResponseEntity<?>  getAssignmentsByProjectID(@PathVariable(value = "id") Integer projectID){
        return ResponseEntity.ok().body(projectAssignmentDAO.findAllByProjectId(projectID));
    }

    @PostMapping(consumes = "application/json")
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
