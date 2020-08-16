package se.team_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import se.team_management.models.Employee;
import se.team_management.servises.EmployeeDAO;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    EmployeeDAO employeeDAO;

    @GetMapping()
    public ResponseEntity<List<Employee>> getUsers(){
        return ResponseEntity.ok().body(employeeDAO.findAll());
    }

    @GetMapping("/ids/{id}")
    public ResponseEntity<Employee>  getUserByID(@PathVariable(value = "id") Integer id){
        return  ResponseEntity.ok().body(employeeDAO.findById(id));
    }

    @GetMapping("/emails/{email}")
    public ResponseEntity<Employee>  getUserByEmail(@PathVariable(value = "email") String email){
        return  ResponseEntity.ok().body(employeeDAO.findByEmail(email));
    }

    @GetMapping("/usernames/{username}")
    public ResponseEntity<Employee>  getUserByUsername(@PathVariable(value = "username") String username){
        return  ResponseEntity.ok().body(employeeDAO.findByUsername(username));
    }

    @PostMapping()
    public ResponseEntity<Employee>  createUser(@RequestBody Employee employee){
        return  ResponseEntity.ok().body(employeeDAO.save(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteUser(@PathVariable(value = "id") Integer id){
        Employee employeeToDelete = employeeDAO.findById(id);
        if (employeeToDelete == null){
            return ResponseEntity.notFound().build();
        }
        employeeDAO.delete(employeeToDelete);
        return ResponseEntity.ok().build();
    }



    @PutMapping("/{id}")
    public ResponseEntity<Employee>  updateUser(@PathVariable(value = "id") Integer id, @RequestBody Employee employee){
        Employee employeeToModify = employeeDAO.findById(id);
        if (employeeToModify == null){
            return ResponseEntity.notFound().build();
        }
        updateUserDetails(employeeToModify, employee);
        return  ResponseEntity.ok().body(employeeDAO.save(employeeToModify));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateUserPartially(@PathVariable(value = "id") Integer id, @RequestBody Map<Object,Object> userData){
        Employee employeeToModify = employeeDAO.findById(id);
        if (employeeToModify == null){
            return ResponseEntity.notFound().build();
        }
        userData.forEach((k,v)->{
            Field field = ReflectionUtils.findField(Employee.class, (String) k);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, employeeToModify,v);
        });
        return  ResponseEntity.ok().body(employeeDAO.save(employeeToModify));
    }

    private void updateUserDetails(Employee employeeToModify, Employee employee) {
        employeeToModify.setUsername(employee.getUsername());
        employeeToModify.setEmail(employee.getEmail());
        employeeToModify.setFirstName(employee.getFirstName());
        employeeToModify.setLastName(employee.getLastName());
        employeeToModify.setRoles(employee.getRoles());
        employeeToModify.setPassword(employee.getPassword());
        employeeToModify.setActive(employee.isActive());
    }
}
