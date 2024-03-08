package com.Valens.api1.DtoModel;

import com.Valens.api1.model.Project;
import jakarta.validation.constraints.NotNull;

import java.util.List;

//@Validated ---> No need of this. Why & When we should've to use @Validated
public class ProjectDto {
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Boolean isActive;

    private List<EmployeeDto> employeeDtoList; // NOTE👉: I will get null in field of projectDtoList of EmployeeDtoList Object. Why ???🚫 Otherwise, it will go to the recursion....🤣It'll never stop🤣

    public List<EmployeeDto> getEmployeeDtoList() {
        return employeeDtoList;
    }

    public void setEmployeeDtoList(List<EmployeeDto> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", employeeDtoList=" + employeeDtoList +
                '}';
    }

    public ProjectDto() {
    }

    public ProjectDto(Integer id, String name, String description, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
    }

    public ProjectDto(int id, String name, String description, boolean isActive) {
    }

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.isActive = project.getActive();
    }

    public ProjectDto(Integer id, String name, String description, Boolean active, List<EmployeeDto> employeeDtoList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = active;
        this.employeeDtoList = employeeDtoList;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

//    @Override
//    public String toString() {
//        return "ProjectDto{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", isActive=" + isActive +
//                '}';
//    }
}
