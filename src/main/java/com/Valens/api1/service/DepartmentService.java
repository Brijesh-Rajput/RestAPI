package com.Valens.api1.service;

import com.Valens.api1.DtoModel.DepartmentDto;
import com.Valens.api1.model.Department;
import com.Valens.api1.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    private Iterable<Department> departmentIterable(Iterator<Department> department){
        return new Iterable<Department>() {
            @Override
            public Iterator<Department> iterator() {
                return department;
            }
        };
    }

    public Collection<DepartmentDto> getDepartments(){
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        departmentRepository.findAll().forEach(department -> departmentDtoList.add(new DepartmentDto(department.getId(),department.getName(),department.getDescription())));
        return departmentDtoList;
    }

    public DepartmentDto getDepartmentById(Integer departmentId) throws Throwable {
        if(departmentRepository.findById(departmentId).isPresent()){
           Department department = departmentRepository.findById(departmentId).get();
           return new DepartmentDto(department.getId(), department.getName(), department.getDescription());
        }
        throw (Throwable) ResponseEntity.badRequest();
    }


    public void createDepartments(List<DepartmentDto> departments) {
        departmentRepository.saveAll(departmentIterable(
                departments.stream().map(departmentDto -> new Department(departmentDto.getName(), departmentDto.getDescription(),LocalDateTime.now().toString(),null)).iterator()
        ));
    }

    public void updateDepartmentById(Integer departmentId, DepartmentDto department) throws Throwable {
        if(!departmentRepository.existsById(departmentId)) throw (Throwable) ResponseEntity.badRequest();
        departmentRepository.save(new Department(departmentId,department.getName(),department.getDescription(),departmentRepository.findById(departmentId).get().getCreatedTime(),LocalDateTime.now().toString()));
    }

    public void deleteDepartmentById(Integer departmentId) throws Throwable {
        if(!departmentRepository.existsById(departmentId)) throw (Throwable) ResponseEntity.badRequest();
        departmentRepository.deleteById(departmentId);
    }
}
