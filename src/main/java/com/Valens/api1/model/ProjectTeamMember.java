package com.Valens.api1.model;

import jakarta.persistence.*;

@Entity
public class ProjectTeamMember {

    // Apply validation like:- @NotNull

    @EmbeddedId
    private ProjectTeamMemberKey projectTeamMemberKey;

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;
//    @JoinColumn(name = "project_id")
//    private ProjectDto projectDto; //--> we can't do these, bcoz: there is no table in database with ProjectDto name. 👇 same for EmployeeDto
    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;
//    @JoinColumn(name = "employee_id")
//    private EmployeeDto employeeDto;

    public ProjectTeamMember() {
    }

    public ProjectTeamMember(ProjectTeamMemberKey projectTeamMemberKey, Project project, Employee employee) {
        System.out.println("Hello from project team member constructor:- called bcoz:- of controller post mapping");
        this.projectTeamMemberKey = projectTeamMemberKey;
        this.project = project; // do i need to send whole object:- I think here, we need only there id, same as in department table when it is a reference in employee table
        this.employee = employee;
    }

    public ProjectTeamMemberKey getProjectTeamMemberKey() {
        return projectTeamMemberKey;
    }

    public void setProjectTeamMemberKey(ProjectTeamMemberKey projectTeamMemberKey) {
        this.projectTeamMemberKey = projectTeamMemberKey;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "ProjectTeamMember{" +
                "projectTeamMemberKey=" + projectTeamMemberKey +
                ", project=" + project +
                ", employee=" + employee +
                '}';
    }

}
