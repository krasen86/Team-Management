package se.team_management.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "task", uniqueConstraints={
        @UniqueConstraint(columnNames = {"title"})}, schema = "public")
public class Task {

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
    Set<Job> jobs;


    public Task(String title, LocalDate startDate, LocalDate endDate, String description) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.completed = false;
    }
    public Task(String title, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = "";
        this.completed = false;
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
        this.jobs = task.getEmployeeTaskJobs();
    }

    public Set<Job> getEmployeeTaskJobs() {
        return jobs;
    }

    public void setEmployeeTaskJobs(Set<Job> jobs) {
        this.jobs = jobs;
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
}
