package com.Valens.api1.service;

import com.Valens.api1.DtoModel.ProjectTeamMemberDto;
import com.Valens.api1.model.Employee;
import com.Valens.api1.model.Project;
import com.Valens.api1.model.ProjectTeamMember;
import com.Valens.api1.model.ProjectTeamMemberKey;
import com.Valens.api1.repository.ProjectTeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTeamMemberService {

    @Autowired
    private ProjectTeamMemberRepository projectTeamMemberRepository;

    public List<ProjectTeamMemberDto> getProjectTeamMembers() {
        List<ProjectTeamMember> projectTeamMemberList = (List<ProjectTeamMember>) projectTeamMemberRepository.findAll(); // we're able to typeCast here, bcoz:- List is also a iterable. --> i guess:- cnf it...
        return projectTeamMemberList.stream().map(projectTeamMember -> new ProjectTeamMemberDto(projectTeamMember)).toList();
    }

    public void addNewProjectTeamMember(ProjectTeamMemberDto projectTeamMemberDto) {
        Integer projectDtoId = projectTeamMemberDto.getProjectDto().getId();
        Integer employeeDtoId = projectTeamMemberDto.getEmployeeDto().getId();
        projectTeamMemberRepository.save(new ProjectTeamMember(new ProjectTeamMemberKey(projectDtoId,employeeDtoId),new Project(projectTeamMemberDto.getProjectDto()),new Employee(projectTeamMemberDto.getEmployeeDto())));
    }


    public void deleteAllProjectTeamMemberByProjectId(Integer projectId) {
        projectTeamMemberRepository.deleteAllByProjectId(projectId); // if projectId is wrong then this method will silently ignore it.
    }
}
