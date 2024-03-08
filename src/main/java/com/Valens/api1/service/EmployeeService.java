package com.Valens.api1.service;

import com.Valens.api1.DtoModel.DepartmentDto;
import com.Valens.api1.DtoModel.EmployeeDto;
import com.Valens.api1.DtoModel.ProjectDto;
import com.Valens.api1.DtoModel.ProjectTeamMemberDto;
import com.Valens.api1.model.Department;
import com.Valens.api1.model.Employee;
import com.Valens.api1.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDto> getEmployees() {
        return ((List<Employee>)employeeRepository.findAll()).stream().map(employee -> new EmployeeDto(employee.getId(),employee.getName(),employee.getEmail(),employee.getBirthDate(),new DepartmentDto(employee.getDepartment()))).toList();
    }

    public void createEmployee(List<EmployeeDto> employeeDtoList) {
//        employeeRepository.saveAll(employeeDtoList.stream().map(employeeDto -> new Employee(employeeDto.getName(),employeeDto.getEmail(),employeeDto.getBirthDate(),employeeDto.getDepartmentDto().getId(), LocalDateTime.now().toString(),null)).toList());
        employeeRepository.saveAll(employeeDtoList.stream().map(employeeDto -> new Employee(employeeDto.getName(),employeeDto.getEmail(),employeeDto.getBirthDate(),new Department(employeeDto.getDepartmentDto()), LocalDateTime.now().toString(),null)).toList()); // bcoz: we need only reference of department_id in employee.
        // saveAll() method needs iterable conent as a parameter into that. i.e. all clasess which are iterable. like:- list,... collection framework classes.
    }

    public EmployeeDto getEmployeeById(Integer employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if(employee == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid employeeId");
//        for(ProjectTeamMember p: employee.getProjectTeamMemberList()) System.out.println(p);
//        return new EmployeeDto(employee.getId(),employee.getName(),employee.getEmail(), employee.getBirthDate(),new DepartmentDto(employee.getDepartment()));
        List<ProjectDto> projectDtoList = employee.getProjectTeamMemberList().stream().map(projectTeamMember -> new ProjectTeamMemberDto(projectTeamMember).getProjectDto()).toList();
        return new EmployeeDto(employee.getId(),employee.getName(),employee.getEmail(), employee.getBirthDate(),new DepartmentDto(employee.getDepartment()),projectDtoList);
    }

    public void updateEmployeeById(Integer employeeId, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null); // I want created time, so that it can't be change while i will update. that's why i need to write this line only for just one paramtere.
        if(employee == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid employeeId");
//        employeeRepository.save(new Employee(employeeId,employeeDto.getName(),employeeDto.getEmail(),employeeDto.getBirthDate(),employeeDto.getDepartmentDto().getId(),employee.getCreatedTime(),LocalDateTime.now().toString())); // Don't forget to pass primary key, i.e. employee_id :- otherwise it will add new element instead of updating existing employee details.
        employeeRepository.save(new Employee(employeeId,employeeDto.getName(),employeeDto.getEmail(),employeeDto.getBirthDate(),new Department(employeeDto.getDepartmentDto()),employee.getCreatedTime(),LocalDateTime.now().toString())); // Don't forget to pass primary key, i.e. employee_id :- otherwise it will add new element instead of updating existing employee details.
    }

    public void deleteEmployeeById(Integer employeeId) {
        employeeRepository.deleteById(employeeId); // NOTE: if employeeId is not exist in the database then springBoot will silently ignored that.
    }
}
// check create & update api ==> validate this