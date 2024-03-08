package com.Valens.api1.model;

import com.Valens.api1.DtoModel.ProjectDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Boolean isActive;

    private String createdTime;

    private String updatedTime;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL) // when we delete project from project table, all related entries will be deleted from ProjectTeamMember table.
    private List<ProjectTeamMember> projectTeamMemberList;

    public List<ProjectTeamMember> getProjectTeamMemberList() {
        return projectTeamMemberList;
    }

    public void setProjectTeamMemberList(List<ProjectTeamMember> projectTeamMemberList) {
        this.projectTeamMemberList = projectTeamMemberList;
    }

    public Project(Integer id, String name, String description, Boolean isActive, String createdTime, String updatedTime, List<ProjectTeamMember> projectTeamMemberList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.projectTeamMemberList = projectTeamMemberList;
    }

    public Project() {
    }

    public Project(String name, String description, Boolean isActive, String createdTime, String updatedTime) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Project(Integer id, String name, String description, Boolean isActive, String createdTime, String updatedTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Project(ProjectDto projectDto) {// for reference
        this.id = projectDto.getId();
        // as, it is only for reference:- that's why i'm not initializing other fields
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
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", createdTime='" + createdTime + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                '}';
    }
}
