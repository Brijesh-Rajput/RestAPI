package com.Valens.api1.controller;

import com.Valens.api1.DtoModel.EmployeeDto;
import com.Valens.api1.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class EmployeeController {

    // Just do validation of employee date of birth by changing its datatype into Date data type.
    // How to handle exception which are coming from spring, when we are inserting entry in child table but we're sending/setting wrong primary key of parent table as a reference key ??? so that user can understand and also we'll get short & crisp exception/error in server side(on server log)
    // Learn how to pass json data of child table or add data into child data(which also have to pass references of primary key of parent table) through postman. see sample 👇
    /*
        {
            "name": "birjhu",
            "email": "rutul@gmail.com",
            "birthDate": "2024/10/23",
            "department": { "id": 2 }
        }
     */
    /*
        {
            "name": "birjhu",
            "email": "rutul@gmail.com",
            "birthDate": "2024/10/23",
            "department": {
                    "id": 2, // if we're giving wrong id which is not exist in the parent table, as it is refering to the primary key of the parent table.--> then it gives 500 status error. --> I've to give BAD_REQUEST error to the user. How can i do that ? ---> I think i will get the same error when i will use POST or update the existing employeee(or child data with invalid reference key value)
                    "name": "CSE", // giving wrong name then also --> it work's :- But it should not have to be, but department id should have to exit in the database.
                    "description": "Computer Science & Engineering"
                }
        }
     */


    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/api/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @GetMapping("/api/employees/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        if(employeeDto == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid employeeId");
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }

    @PostMapping("/api/employees")
    public ResponseEntity<String> createEmployee(@RequestBody @Valid List<EmployeeDto> employeeDtoList){
        employeeService.createEmployee(employeeDtoList);
        return new ResponseEntity<>("Employees are created successfully!",HttpStatus.OK);
    }

    @PutMapping("/api/employees/{employeeId}")
    public ResponseEntity<String> updateEmployeeById(@PathVariable Integer employeeId, @RequestBody @Valid EmployeeDto employeeDto){
        employeeService.updateEmployeeById(employeeId,employeeDto);
        return new ResponseEntity<>("Employee is successfully updated!",HttpStatus.OK);
    }

    @DeleteMapping("/api/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer employeeId){
        employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>("Employee is deleted successfully!", HttpStatus.OK);
    }

}

