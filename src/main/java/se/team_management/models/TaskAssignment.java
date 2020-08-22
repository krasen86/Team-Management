package se.team_management.models;

import javax.persistence.*;

@Entity
@Table(name = "task_assignment", schema = "public")
public class TaskAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_assignment_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    private long timeWorkedInMinutes;

    public TaskAssignment() {
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

    public long getTimeWorkedInMinutes() {
        return timeWorkedInMinutes;
    }

    public void setTimeWorkedInMinutes(long timeWorkedInMinutes) {
        this.timeWorkedInMinutes = timeWorkedInMinutes;
    }
}
