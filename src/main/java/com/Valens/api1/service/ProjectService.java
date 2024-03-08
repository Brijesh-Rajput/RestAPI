package com.Valens.api1.service;

import com.Valens.api1.DtoModel.EmployeeDto;
import com.Valens.api1.DtoModel.ProjectDto;
import com.Valens.api1.DtoModel.ProjectTeamMemberDto;
import com.Valens.api1.model.Employee;
import com.Valens.api1.model.Project;
import com.Valens.api1.model.ProjectTeamMember;
import com.Valens.api1.model.ProjectTeamMemberKey;
import com.Valens.api1.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    public List<ProjectDto> getprojects() {
        return ((List<Project>) projectRepository.findAll()).stream().map(project -> new ProjectDto(project.getId(),project.getName(),project.getDescription(),project.getActive())).toList();
    }

    public ProjectDto getProjectById(Integer projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid ProjectId");
        List<EmployeeDto> employeeDtoList = project.getProjectTeamMemberList().stream().map(projectTeamMember -> new ProjectTeamMemberDto(projectTeamMember).getEmployeeDto()).toList();
//        return new ProjectDto(project.getId(),project.getName(), project.getDescription(), project.getActive());
        return new ProjectDto(project.getId(),project.getName(), project.getDescription(), project.getActive(), employeeDtoList);
    }

    public void createProject(List<ProjectDto> projectDtoList) {
        List<Project> projectList = (List<Project>) projectRepository.saveAll(projectDtoList.stream().map(projectDto -> new Project(projectDto.getName(),projectDto.getDescription(),projectDto.getActive(), LocalDateTime.now().toString(),null)).toList()); // we don't need to set id for Project entry, bcoz: it will be auto increment by database.(as none is set in application properties in ddl generation)

        for(int i=0; i<projectDtoList.size(); i++){
            // another way is to save it using ProjectTeamMemberRepository or projectRepository ???
            Project project = projectList.get(i);
            System.out.println(project);
            project.setProjectTeamMemberList(projectDtoList.get(i).getEmployeeDtoList().stream().map(employeeDto -> new ProjectTeamMember(new ProjectTeamMemberKey(project.getId(), employeeDto.getId()),project,new Employee(employeeDto))).collect(Collectors.toCollection(ArrayList::new))); // Why we need another supplier ? Why it is not working with .toList() ?? What's the difference ?? How it's a immutable ? when we use .toList() ?? Why we need immutable list here ??
            System.out.println(project);
            projectRepository.save(project);
        }

//        List<Project> projectList = (List<Project>) projectRepository.saveAll(projectDtoList.stream().map(projectDto -> {
//            Project project = new Project(projectDto.getName(),projectDto.getDescription(),projectDto.getActive(), LocalDateTime.now().toString(),null);
//            project.setProjectTeamMemberList(projectDto.getEmployeeDtoList().stream().map(employeeDto -> new ProjectTeamMember(new ProjectTeamMemberKey(project.getId(), employeeDto.getId()),project,new Employee(employeeDto))).toList());
//            return project;
//        }).toList());

//        //🚫🚫🚫🚫⛔⛔⛔⛔⛔ 👇 This will store the dat only in server memory, Not into our actual database.
//        for(int i=0; i<projectDtoList.size(); i++){
//            // another way is to save it using ProjectTeamMemberRepository ???
//            Project project = projectList.get(i);
//            project.setProjectTeamMemberList(projectDtoList.get(i).getEmployeeDtoList().stream().map(employeeDto -> new ProjectTeamMember(new ProjectTeamMemberKey(project.getId(), employeeDto.getId()),project,new Employee(employeeDto))).toList());
//        }
    }

    public void updateProjectById(Integer projectId, ProjectDto projectDto) {
//        System.out.println(projectDto);
        Project project = projectRepository.findById(projectId).orElse(null); // How to avoid this:- i.e. we can use existById method to check whether Id is exist or not. // We're storing project object bcoz:- we don't need to update project.created_time. we want to store it as it is. How to update partial column using save() method of Crud Repository. Same thing/doubt in all related api's.
        if(project == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid project Id");
//        projectRepository.save(new Project(projectId,projectDto.getName(),projectDto.getDescription(),projectDto.getActive(),project.getCreatedTime(),LocalDateTime.now().toString()));
        List<ProjectTeamMember> projectTeamMemberList = projectDto.getEmployeeDtoList().stream().map(employeeDto -> new ProjectTeamMember(new ProjectTeamMemberKey(project.getId(), employeeDto.getId()),project,new Employee(employeeDto))).toList();
        projectRepository.save(new Project(projectId,projectDto.getName(),projectDto.getDescription(),projectDto.getActive(),project.getCreatedTime(),LocalDateTime.now().toString(),projectTeamMemberList));
    }

    public void deleteProjectById(Integer projectId) {
        if(!projectRepository.existsById(projectId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid project Id");
        projectRepository.deleteById(projectId);
    }
}
