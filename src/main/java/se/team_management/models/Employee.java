package se.team_management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee_accounts", uniqueConstraints={
        @UniqueConstraint(columnNames = {"username", "email"})
}, schema = "public")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Integer id;
    @NotBlank
    private String username;
    @NotBlank @JsonIgnore
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Email
    @NotBlank
    private String email;
    @Column(name = "last_name")
    private String lastName;
    private String roles;
    private boolean active;
    @OneToMany(mappedBy = "employee")
    @JsonManagedReference(value = "taskAssignments")
    private Set<TaskAssignment> taskAssignments;
    @OneToMany(mappedBy = "employee")
    @JsonManagedReference(value = "projectAssignments")
    private Set<ProjectAssignment> projectAssignments;

    public Employee() {
    }

    public Employee(Employee employee) {
        this.id = employee.id;
        this.username = employee.username;
        this.password = employee.password;
        this.firstName = employee.firstName;
        this.email = employee.email;
        this.lastName = employee.lastName;
        this.roles = employee.roles;
        this.active = employee.active;
        this.taskAssignments = employee.taskAssignments;
        this.projectAssignments = employee.projectAssignments;
    }

    public Employee(String username, String email, String password) {
        this.username = username;
        setPassword(password);
        this.firstName = "N/A";
        this.email = email;
        this.lastName = "N/A";
        this.roles = Role.ROLE_USER.name();
        this.active = true;
        this.taskAssignments = new HashSet<>();
        this.projectAssignments = new HashSet<>();
    }

    public Set<TaskAssignment> getTaskAssignments() {
        return taskAssignments;
    }

    public void setTaskAssignments(Set<TaskAssignment> taskAssignments) {
        this.taskAssignments = taskAssignments;
    }

    public Set<ProjectAssignment> getProjectAssignments() {
        return projectAssignments;
    }

    public void setProjectAssignments(Set<ProjectAssignment> projectAssignments) {
        this.projectAssignments = projectAssignments;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode( password);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", active=" + active +
                '}';
    }
}
