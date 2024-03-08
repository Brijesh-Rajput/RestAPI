package com.Valens.api1.repository;

import com.Valens.api1.model.ProjectTeamMember;
import com.Valens.api1.model.ProjectTeamMemberKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProjectTeamMemberRepository extends CrudRepository<ProjectTeamMember, ProjectTeamMemberKey> { // 2nd parameter is the type of the primary key of 1st parameter table(i.e. ProjectTeamMember Table)
    @Transactional
    void deleteAllByProjectId(Integer projectId);

    @Transactional
    void deleteAllByEmployeeId(Integer employeeId);
}
