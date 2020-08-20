package se.team_management.models.auth;

import se.team_management.models.Employee;

public class JwtResponse {

    private String token;
    private Employee employee;

    public JwtResponse(String token, Employee employee) {
        this.token = token;
        this.employee = employee;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
