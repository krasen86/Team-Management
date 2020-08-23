package se.team_management.models;

import javax.persistence.*;
@Entity
@Table(name = "project_assignment", schema = "public")
public class ProjectAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_assignment_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public ProjectAssignment() {
    }

    public ProjectAssignment(Employee employee, Project project) {
        this.employee = employee;
        this.project = project;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
