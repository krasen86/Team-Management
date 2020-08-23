package se.team_management.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "project", uniqueConstraints={
        @UniqueConstraint(columnNames = {"project_name"})}, schema = "public")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private Integer id;
    @Column(name = "project_name") @NotBlank
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
    @OneToMany(mappedBy = "project")
    @JsonManagedReference(value = "projectAssignments")
    private Set<ProjectAssignment> projectAssignments;
    @OneToMany(mappedBy = "project")
    @JsonManagedReference(value = "tasks")
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
        this.projectAssignments = project.projectAssignments;
        this.tasks = project.tasks;
    }

    public Project(String name, LocalDate startDate, LocalDate endDate ,String description) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = true;
        this.projectAssignments = new HashSet<ProjectAssignment>();
        this.tasks = new HashSet<>();
    }
    public Project(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.description = "N/A";
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = true;
        this.projectAssignments = new HashSet<ProjectAssignment>();
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

    public Set<ProjectAssignment> getProjectAssignments() {
        return projectAssignments;
    }

    public void setProjectAssignments(Set<ProjectAssignment> employees) {
        this.projectAssignments = employees;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
