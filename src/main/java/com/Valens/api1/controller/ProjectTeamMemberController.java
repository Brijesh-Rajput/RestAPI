package com.Valens.api1.controller;

import com.Valens.api1.DtoModel.ProjectTeamMemberDto;
import com.Valens.api1.service.ProjectTeamMemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectTeamMemberController {

    @Autowired
    private ProjectTeamMemberService projectTeamMemberService;

    @GetMapping("/api/project_teams")
    public ResponseEntity<List<ProjectTeamMemberDto>> getProjectTeamMembers(){
        return new ResponseEntity<>(projectTeamMemberService.getProjectTeamMembers(), HttpStatus.OK);
    }

    @PostMapping("/api/project_teams") // No need of ProjectTeamMemberDto class --> bcoz:- there's no column in database which we don't want to show to the user.
    public ResponseEntity<String> addNewProjectTeamMember(@RequestBody @Valid ProjectTeamMemberDto projectTeamMemberDto){ // How you'll validate the incoming object ?? --> Think:- Where you'll use @NotNull anotation in ProjectTeamMember Class.
        projectTeamMemberService.addNewProjectTeamMember(projectTeamMemberDto);
        return new ResponseEntity<>("New project Team Member is successfully Added!",HttpStatus.OK);
    }

    @DeleteMapping("/api/project_teams/{projectId}")
    public ResponseEntity<String> deleteAllProjectTeamMemberByProjectId(@PathVariable Integer projectId){
        projectTeamMemberService.deleteAllProjectTeamMemberByProjectId(projectId);
        return new ResponseEntity<>("Project Team Member is successfully deleted!",HttpStatus.OK);
    }
}
