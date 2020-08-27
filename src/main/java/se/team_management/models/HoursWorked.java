package se.team_management.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hours_worked", schema = "public")
public class HoursWorked {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hours_worked_id")
    private Integer id;
    @Column(name = "date_worked")
    private LocalDate dateWorked;
    @Column(name = "hours_worked")
    private float hoursWorked;
    @ManyToOne
    @JoinColumn(name = "task_assignment_id")
    @JsonBackReference(value = "taskAssignment")
    @Column(name = "task_assignment")
    private TaskAssignment taskAssignment;

    public HoursWorked() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateWorked() {
        return dateWorked;
    }

    public void setDateWorked(LocalDate dateWorked) {
        this.dateWorked = dateWorked;
    }

    public float getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(float hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public TaskAssignment getTaskAssignment() {
        return taskAssignment;
    }

    public void setTaskAssignment(TaskAssignment taskAssignment) {
        this.taskAssignment = taskAssignment;
    }

    @Override
    public String toString() {
        return "HoursWorked{" +
                "id=" + id +
                ", dateWorked=" + dateWorked +
                ", hoursWorked=" + hoursWorked +
                ", taskAssignment=" + taskAssignment +
                '}';
    }
}
