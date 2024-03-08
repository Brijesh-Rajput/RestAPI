package com.Valens.api1.model;


import com.Valens.api1.DtoModel.DepartmentDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Department {

//    @NotNull  ===> if it was @NotNull then, we can't create an object of this Department class with ignoring id value(i.e. whenever we'll create an object, we should've the value in "id" field.)
    // 👆 I'think @NotNull is a constraints for table entry value. which is JPA(jakarata persistence api) constraints.
    // @Valid is constraints to check whether an object is math with all the constraints given in that class(or model)
    // I think, if 👆 this is correct, then saveAll() method internally also checks using JPA @Valid anotation.
    // Yes, I confirm. See Department controller(in put method), when i use @Valid anotation I'm not able to reach to the e.printStack() method. Validation is happening in parameter.
    // And When, I'm not using @valid anotation in parameter, validation is happening in the save method of respective repository implemented class.

    // similarly, Validate all the input's coming as a Request in all the controller. 👈 ToDo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // make this field as required
    @NotNull
    private String name;

    @NotNull
    private String description;

    private String createdTime;

    private String updatedTime;

    public Department() {
    }

    public Department(Integer id, String name, String description, String createdTime, String updatedTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Department(String name, String description, String createdTime, String updatedTime) {
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Department(DepartmentDto departmentDto) {
        this.id = departmentDto.getId();
//        this.name = departmentDto.getName(); // I think :-  we don't need :--- we only need reference or departmentid
//        this.description = departmentDto.getDescription(); // I think :- We don't need this:- as department is reference variable in
    }
//    public Department(Integer departmentDtoReferenceId){
//        this.id = departmentDtoReferenceId;
//    }

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
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", updatedTime='" + updatedTime + '\'' +
                '}';
    }
}
