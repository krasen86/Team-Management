package se.team_management.models.auth;

import se.team_management.models.Employee;

public class JwtResponse {

    private String accessToken;
    private Employee employee;

    public JwtResponse(String accessToken, Employee employee) {
        this.accessToken = accessToken;
        this.employee = employee;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
