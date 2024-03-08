package com.Valens.api1.DtoModel;

import com.Valens.api1.model.ProjectTeamMember;
import jakarta.validation.constraints.NotNull;

public class ProjectTeamMemberDto {

    @NotNull
    private ProjectDto projectDto; // Why ProjectDto type ? Why not Integer bcoz: we only need projectID as a reference ? Reason:- Bcoz:- We also want to show data of ProjectDto type not only their Id. --> another way is to create another model for showing output(i.e. for get mapping)

    @NotNull
    private EmployeeDto employeeDto;

    public ProjectTeamMemberDto() {
    }

    public ProjectTeamMemberDto(ProjectDto projectDto, EmployeeDto employeeDto) {
        this.projectDto = projectDto;
        this.employeeDto = employeeDto;
    }

    public ProjectTeamMemberDto(ProjectTeamMember projectTeamMember) {
        this.employeeDto = new EmployeeDto(projectTeamMember.getEmployee());
        this.projectDto = new ProjectDto(projectTeamMember.getProject());
    }

    public ProjectDto getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectDto projectDto) {
        this.projectDto = projectDto;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    @Override
    public String toString() {
        return "ProjectTeamMemberDto{" +
                "projectDto=" + projectDto +
                ", employeeDto=" + employeeDto +
                '}';
    }
}
