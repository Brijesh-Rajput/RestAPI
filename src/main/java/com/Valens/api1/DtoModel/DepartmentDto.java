package com.Valens.api1.DtoModel;

import com.Valens.api1.model.Department;
import jakarta.validation.constraints.NotNull;

//@Getter @Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
public class DepartmentDto {
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    public DepartmentDto() {
    }

    public DepartmentDto(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public DepartmentDto(Department department) { // for converting :- reference column key output
        this.id = department.getId();
        this.name = department.getName();
        this.description = department.getDescription();
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

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
