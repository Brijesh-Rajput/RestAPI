package com.Valens.api1.controller;

import com.Valens.api1.DtoModel.ProjectDto;
import com.Valens.api1.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ProjectController {
    // How to handle error or exception or customize error msg for user ? coming from spring or spring boot framework... :- Then you've to create your own exception class & handle. But, we require exception class when we need to go beyond HTTPstatus response or should provide customize msg to user instead of spring handles the exception(exception that thrown by spring framework).
    // should i've to response NOT_FOUND or BAD_REQUEST :- It's depend on project. Arpan sir:- prefer:- BAD_REQUEST, e.g:- DOS attack :- if hacker will get NOT_FOUND then it guarantees that request is successfull but data is not present in the database.
    @Autowired
    private ProjectService projectService;
    @GetMapping("/api/projects")
    public ResponseEntity<List<ProjectDto>> getProjects(){
        return new ResponseEntity<>(projectService.getprojects(),HttpStatus.OK);
    }

    @GetMapping("/api/projects/{projectId}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Integer projectId){
        if(projectId == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"projectId can't be null"); // Bad request or Not found ? // Depends on project. But, sir prefer BAD_REQUEST
        return new ResponseEntity<>(projectService.getProjectById(projectId), HttpStatus.OK);
    }

    @PostMapping("/api/projects")
    public ResponseEntity<String> createProject(@RequestBody @Valid List<ProjectDto> projectDtoList){
        projectService.createProject(projectDtoList);
        return new ResponseEntity<>("Project created succesfully!",HttpStatus.OK);
    }

    @PutMapping("/api/projects/{projectId}")
    public ResponseEntity<String> updateProjectById(@PathVariable Integer projectId, @RequestBody @Valid ProjectDto projectDto){
        if(projectId == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"projectId can't be null");
//        System.out.println(projectDto);
        projectService.updateProjectById(projectId, projectDto);
        return new ResponseEntity<>("Project updated successfully",HttpStatus.OK);
    }

    @DeleteMapping("/api/projects/{projectId}")
    public ResponseEntity<String> deleteProjectById(@PathVariable Integer projectId){
        if(projectId == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"projectId can't be null"); // Bad Request or Not found request
        projectService.deleteProjectById(projectId);
        return new ResponseEntity<>("project deleted successfully", HttpStatus.OK);
    }

}
