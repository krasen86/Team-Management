package se.team_management.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project", uniqueConstraints={
        @UniqueConstraint(columnNames = {"project_name"})}, schema = "public")
public class Project {

    @Id
    @Column(name = "project_id")
    private Integer id;
    @Column(name = "project_name") @NotBlank
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
    @OneToMany(mappedBy = "project")
    private Set<Employee> employees;
    @OneToMany(mappedBy = "project")
    private Set<Task> tasks;

    public Project() {
    }

    public Project(Project project) {
        this.id = project.id;
        this.name = project.name;
        this.description = project.description;
        this.startDate = project.startDate;
        this.endDate = project.endDate;
        this.active = project.active;
        this.employees = project.employees;
        this.tasks = project.tasks;
    }

    public Project(String name, LocalDate startDate, LocalDate endDate ,String description) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = true;
        this.employees = new HashSet<>();
        this.tasks = new HashSet<>();
    }
    public Project(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.description = "N/A";
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = true;
        this.employees = new HashSet<>();
        this.tasks = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
