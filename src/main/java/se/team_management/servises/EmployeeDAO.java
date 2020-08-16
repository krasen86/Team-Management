package se.team_management.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.team_management.models.Employee;
import se.team_management.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDAO {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findByUsername(String username){
        Optional<Employee> user = employeeRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return  user.map(Employee::new).get();
    }
    
    public Employee findById(Integer id){
        Optional<Employee> user = employeeRepository.findById(id);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + id));
        return  user.map(Employee::new).get();
    }

    public Employee findByEmail(String email){
        Optional<Employee> user = employeeRepository.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return user.map(Employee::new).get();
    }

//    public User findAllByFirstName(String firstName){
//        Optional<List<User>> user = userRepository.findAllByFirstName(email);
//        user.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + firstName));
//        return  user.map(User::new).get();
//    }



    public void delete(Employee employee){
        employeeRepository.delete(employee);
    }

    public boolean findIfExistsByUsername(String username){
        return employeeRepository.existsByUsername(username);
    }

    public boolean findIfExistsByEmail(String email){
        return employeeRepository.existsByEmail(email);
    }
}
