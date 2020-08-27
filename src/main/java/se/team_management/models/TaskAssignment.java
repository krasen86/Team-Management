package se.team_management.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "task_assignment", schema = "public")
public class TaskAssignment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_assignment_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference(value = "employee")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "task_id")
    @JsonBackReference(value = "task")
    private Task task;


    public TaskAssignment() {
    }

    public TaskAssignment(Employee employee, Task task) {
        this.employee = employee;
        this.task = task;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
