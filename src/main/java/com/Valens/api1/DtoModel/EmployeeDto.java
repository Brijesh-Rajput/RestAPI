package com.Valens.api1.DtoModel;

import com.Valens.api1.model.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class EmployeeDto {

    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  // replace datatype of "birthDate" into Date datatype
    private String birthDate;

    @NotNull
    private DepartmentDto departmentDto;

//    private List<ProjectTeamMemberDto> projectTeamMemberDtoList; // Due to this:- projectTeamMemberDtoList will include of object of employee again in projectTeamMemberDto --> like recursion 🤣😂 NOTE👉:- We only want list of projectDto.
    private List<ProjectDto> projectDtoList; // NOTE👉: I will get null in field of employeeDtoList of ProjectDtoList. Why ???🚫 Otherwise, it will go to the recursion....🤣It'll never stop🤣

    public List<ProjectDto> getProjectDtoList() {
        return projectDtoList;
    }

    public void setProjectDtoList(List<ProjectDto> projectDtoList) {
        this.projectDtoList = projectDtoList;
    }

    public EmployeeDto() {
    }


    public EmployeeDto(Integer id, String name, String email, String birthDate, DepartmentDto departmentDto) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.departmentDto = departmentDto;
    }

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.birthDate = employee.getBirthDate();
        this.departmentDto = new DepartmentDto(employee.getDepartment());
    }

    public EmployeeDto(Integer id, String name, String email, String birthDate, DepartmentDto departmentDto, List<ProjectDto> projectDtoList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate= birthDate;
        this.departmentDto = departmentDto;
        this.projectDtoList = projectDtoList;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", department=" + departmentDto +
                '}';
    }

}
