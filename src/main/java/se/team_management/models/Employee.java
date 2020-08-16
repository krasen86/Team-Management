package se.team_management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.validation.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "user_account", schema = "public")
public class Employee implements Serializable {

    @Id @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank
    private String username;
    @NotBlank
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
    }

    public Employee(String username, String email, String password) {
        this.username = username;
        setPassword(password);
        this.firstName = "N/A";
        this.email = email;
        this.lastName = "N/A";
        this.roles = Role.ROLE_USER.name();
        this.active = true;
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
