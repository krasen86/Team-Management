package se.team_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import se.team_management.models.Project;
import se.team_management.models.Task;
import se.team_management.servises.ProjectDAO;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Autowired
    ProjectDAO projectDAO;

    @GetMapping()
    public ResponseEntity<List<Project>> getTasks(){
        return ResponseEntity.ok().body(projectDAO.findAll());
    }

    @GetMapping("/ids/{id}")
    public ResponseEntity<Project>  getProjectByID(@PathVariable(value = "id") Integer id){
        return  ResponseEntity.ok().body(projectDAO.findById(id));
    }


    @GetMapping("/names/{name}")
    public ResponseEntity<Project>  getTaskByTitle(@PathVariable(value = "name") String name){
        return  ResponseEntity.ok().body(projectDAO.findByName(name));
    }

    @PostMapping()
    public ResponseEntity<?>  createProject(@RequestBody Project newProject){
        if (projectDAO.findIfExistsByName(newProject.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body("Task title is already taken!");
        }
        Project project;
        if (newProject.getDescription() == null){
            project = new Project(newProject.getName(),newProject.getStartDate(),newProject.getEndDate());
        }
        else {
            project = new Project(newProject.getName(),newProject.getStartDate(),newProject.getEndDate(), newProject.getDescription());
        }
        return  ResponseEntity.ok().body(projectDAO.save(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable(value = "id") Integer id){
        Project projectToDelete = projectDAO.findById(id);
        if (projectToDelete == null){
            return ResponseEntity.notFound().build();
        }
        projectDAO.delete(projectToDelete);
        return ResponseEntity.ok().build();
    }



    @PutMapping("/{id}")
    public ResponseEntity<Project>  updateProject(@PathVariable(value = "id") Integer id, @RequestBody Project project){
        Project projectToModify = projectDAO.findById(id);
        if (projectToModify == null){
            return ResponseEntity.notFound().build();
        }
        updateTaskDetails(projectToModify, project);
        return  ResponseEntity.ok().body(projectDAO.save(projectToModify));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Project> updateProjectPartially(@PathVariable(value = "id") Integer id, @RequestBody Map<Object,Object> projectData){
        Project projectToModify = projectDAO.findById(id);
        if (projectToModify == null){
            return ResponseEntity.notFound().build();
        }
        projectData.forEach((k,v)->{
            Field field = ReflectionUtils.findField(Task.class, (String) k);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, projectToModify,v);
        });
        return  ResponseEntity.ok().body(projectDAO.save(projectToModify));
    }

    private void updateTaskDetails(Project projectToModify, Project project) {
        projectToModify.setName(project.getName());
        projectToModify.setDescription(project.getDescription());
        projectToModify.setActive(project.isActive());
        projectToModify.setStartDate(project.getStartDate());
        projectToModify.setEndDate(project.getEndDate());

    }
}