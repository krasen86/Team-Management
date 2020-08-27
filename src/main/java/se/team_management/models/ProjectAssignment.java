package se.team_management.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "project_assignment", schema = "public")
public class ProjectAssignment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_assignment_id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference(value = "employee")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "project_id")
    @JsonBackReference(value = "project")
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

    @Override
    public String toString() {
        return "ProjectAssignment{" +
                "id=" + id +
                ", employee=" + employee +
                ", project=" + project +
                '}';
    }
}
