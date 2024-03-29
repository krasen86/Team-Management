package se.team_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import se.team_management.models.Project;
import se.team_management.models.Task;
import se.team_management.models.TaskAssignment;
import se.team_management.servises.ProjectDAO;
import se.team_management.servises.TaskAssignmentDAO;
import se.team_management.servises.TaskDAO;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskDAO taskDAO;
    @Autowired
    private ProjectDAO projectDAO;
    @Autowired
    private TaskAssignmentDAO taskAssignmentDAO;

    @GetMapping()
    public ResponseEntity<List<Task>> getTasks(){
        return ResponseEntity.ok().body(taskDAO.findAll());
    }

    @GetMapping("/ids/{id}")
    public ResponseEntity<Task>  getTaskByID(@PathVariable(value = "id") Integer id){
        return  ResponseEntity.ok().body(taskDAO.findById(id));
    }


    @GetMapping("/titles/{title}")
    public ResponseEntity<Task>  getTaskByTitle(@PathVariable(value = "title") String title){
        return  ResponseEntity.ok().body(taskDAO.findByTitle(title));
    }

    @PostMapping()
    public ResponseEntity<?>  createTask(@RequestBody Task newTask){
        if (checkIfProjectContainsTask(newTask)) {
            return ResponseEntity
                    .badRequest()
                    .body("Task title is already taken!");
        }
        Task task;
        if (newTask.getDescription() == null){
            task = new Task(newTask.getTitle(),newTask.getStartDate(),newTask.getEndDate(), newTask.getProject());
        }
        else {
            task = new Task(newTask.getTitle(),newTask.getStartDate(),newTask.getEndDate(),newTask.getProject(), newTask.getDescription());
        }
        return  ResponseEntity.ok().body(taskDAO.save(task));
    }

    private boolean checkIfProjectContainsTask(Task task){
        Project project = projectDAO.findById(task.getProject().getId());
        Set<Task> tasks = project.getTasks();
        return tasks.contains(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable(value = "id") Integer id){
        Task taskToDelete = taskDAO.findById(id);
        if (taskToDelete == null){
            return ResponseEntity.notFound().build();
        }
        taskDAO.delete(taskToDelete);
        return ResponseEntity.ok().build();
    }



    @PutMapping("/{id}")
    public ResponseEntity<Task>  updateTask(@PathVariable(value = "id") Integer id, @RequestBody Task task){
        Task taskToModify = taskDAO.findById(id);
        if (taskToModify == null){
            return ResponseEntity.notFound().build();
        }
        updateTaskDetails(taskToModify, task);
        return  ResponseEntity.ok().body(taskDAO.save(taskToModify));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTaskPartially(@PathVariable(value = "id") Integer id, @RequestBody Map<Object,Object> taskData){
        Task taskToModify = taskDAO.findById(id);
        if (taskToModify == null){
            return ResponseEntity.notFound().build();
        }
        taskData.forEach((k,v)->{
            if (String.valueOf(k).contains("startDate")){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(String.valueOf(v), formatter);
                taskToModify.setStartDate(localDate);
            }
            else if (String.valueOf(k).contains("endDate")){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(String.valueOf(v), formatter);
                taskToModify.setEndDate(localDate);
            }
            else {
                Field field = ReflectionUtils.findField(Task.class, (String) k);
                assert field != null;
                field.setAccessible(true);
                ReflectionUtils.setField(field, taskToModify, v);
            }
        });
        return  ResponseEntity.ok().body(taskDAO.save(taskToModify));
    }

    private void updateTaskDetails(Task taskToModify, Task task) {
        taskToModify.setTitle(task.getTitle());
        taskToModify.setDescription(task.getDescription());
        taskToModify.setCompleted(task.isCompleted());
        taskToModify.setStartDate(task.getStartDate());
        taskToModify.setEndDate(task.getEndDate());

    }

    @PostMapping("/assignments")
    public ResponseEntity<?> createAssignment(@RequestBody TaskAssignment taskAssignment){
        if (taskAssignmentDAO.findIfExistsByEmployeeIdAndTaskId(taskAssignment.getEmployee().getId(),taskAssignment.getTask().getId())) {
            return ResponseEntity
                    .badRequest()
                    .body("Assignment already exists!");
        }
        TaskAssignment newTaskAssignment;
        newTaskAssignment = new TaskAssignment(taskAssignment.getEmployee(), taskAssignment.getTask());

        return  ResponseEntity.ok().body(taskAssignmentDAO.save(newTaskAssignment));
    }
}
