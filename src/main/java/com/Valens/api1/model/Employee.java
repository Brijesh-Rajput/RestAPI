package com.Valens.api1.model;

import com.Valens.api1.DtoModel.EmployeeDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String birthDate;

    @ManyToOne
    @NotNull
//    @JoinColumn(name = "department_id") // No need:- ByDefault name is "department_id"
    private Department department;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.REMOVE)
    private List<ProjectTeamMember> projectTeamMemberList;

    public List<ProjectTeamMember> getProjectTeamMemberList() {
        return projectTeamMemberList;
    }

    public void setProjectTeamMemberList(List<ProjectTeamMember> projectTeamMemberList) {
        this.projectTeamMemberList = projectTeamMemberList;
    }

    private String createdTime;

    private String updatedTime;

    public Employee() {
    }


    public Employee(String name, String email, String birthDate, Department department, String createdTime, String updatedTime) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.department = department;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Employee(Integer id, String name, String email, String birthDate, Department department, String createdTime, String updatedTime) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.department = department;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Employee(EmployeeDto employeeDto) {// only for reference
        this.id = employeeDto.getId();
        // Not initializing other fields as it is only for reference.
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", department=" + department +
                ", createdTime='" + createdTime + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                '}';
    }

}
