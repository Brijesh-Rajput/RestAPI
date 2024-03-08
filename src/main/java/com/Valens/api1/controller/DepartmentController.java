package com.Valens.api1.controller;

import com.Valens.api1.DtoModel.DepartmentDto;
import com.Valens.api1.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/api/departments")
    public ResponseEntity<Collection<DepartmentDto>> getDepartments(){
        return new ResponseEntity<>(departmentService.getDepartments(), HttpStatus.OK);
    }

    @GetMapping("/api/departments/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Integer departmentId){
        try {
            return new ResponseEntity<>(departmentService.getDepartmentById(departmentId), HttpStatus.OK);
        } catch (Throwable e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"departmentId is not exist!");
        }
    }

    @PostMapping("/api/departments")
    public ResponseEntity<String> createDepartments(@RequestBody @Valid List<DepartmentDto> departments){
        departmentService.createDepartments(departments);
        return new ResponseEntity<>("departments are added succesfully!" , HttpStatus.OK);
    }

    @PutMapping("/api/departments/{departmentId}")
    public ResponseEntity<String> updateDepartmentById(@PathVariable Integer departmentId,@RequestBody @Valid DepartmentDto department){
        System.out.println("Hello Alliens :---->  " + department);
        try {
            departmentService.updateDepartmentById(departmentId, department);
            return new ResponseEntity<>("updated succesfully!", HttpStatus.OK);
        } catch (Throwable e) {
//            System.out.println(e);
//            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"departmentId is not exist!");
        }
    }

    @DeleteMapping("/api/departments/{departmentId}")
    public ResponseEntity<String> deleteDepartmentById(@PathVariable Integer departmentId){
        try {
            departmentService.deleteDepartmentById(departmentId);
            return new ResponseEntity<>("Deleted succesfully!", HttpStatus.OK);
        } catch (Throwable e) {
            ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.BAD_REQUEST,"departmentId is not exist!");
            responseStatusException.setStackTrace(new StackTraceElement[0]);
            throw responseStatusException;
        }
    }

}

