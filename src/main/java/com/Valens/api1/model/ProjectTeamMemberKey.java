package com.Valens.api1.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProjectTeamMemberKey implements Serializable {

    // Apply validation like:- @NotNull

//    @Column(name = "project_id")
    private Integer projectId;
    private Integer employeeId;

    public ProjectTeamMemberKey() {
    }

    public ProjectTeamMemberKey(Integer projectId, Integer employeeId) {
        this.projectId = projectId;
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectTeamMemberKey that)) return false;
        return Objects.equals(getProjectId(), that.getProjectId()) && Objects.equals(getEmployeeId(), that.getEmployeeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectId(), getEmployeeId());
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "ProjectTeamMemberKey{" +
                "projectId=" + projectId +
                ", employeeId=" + employeeId +
                '}';
    }
}
