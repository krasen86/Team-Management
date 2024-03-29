package se.team_management.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "task", schema = "public")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private Integer id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private boolean completed;
    @OneToMany(mappedBy = "task")
    private Set<TaskAssignment> taskAssignments;
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonBackReference(value = "project")
    private Project project;



    public Task(String title, LocalDate startDate, LocalDate endDate, Project project, String description) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.completed = false;
        this.taskAssignments = new HashSet<>();
        this.project = project;
    }
    public Task(String title, LocalDate startDate, LocalDate endDate, Project project) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = "";
        this.completed = false;
        this.taskAssignments = new HashSet<>();
        this.project = project;
    }

    public Task() {
    }

    public Task(Task task) {
        this.id = task.id;
        this.title = task.title;
        this.startDate = task.startDate;
        this.endDate = task.endDate;
        this.description = task.description;
        this.completed = task.completed;
        this.taskAssignments = task.getTaskAssignments();
        this.project = task.project;
    }

    public Set<TaskAssignment> getTaskAssignments() {
        return taskAssignments;
    }

    public void setTaskAssignments(Set<TaskAssignment> taskAssignments) {
        this.taskAssignments = taskAssignments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
