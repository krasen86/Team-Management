package se.team_management.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import se.team_management.models.EmployeeDetails;
import se.team_management.models.Employee;

@Service
public class EmployeeDetailService implements UserDetailsService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Employee employee = employeeDAO.findByUsername(username);
        return new EmployeeDetails(employee);
    }
}
