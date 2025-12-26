package com.app.Recap.Controllers;

import com.app.Recap.DTOs.EmployeeDto;
import com.app.Recap.Entities.Employee;
import com.app.Recap.services.IEmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/api")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * @return welcome message
     */
    @GetMapping("/")
    public ResponseEntity<String> getWelcome() {
        return new ResponseEntity<>("Welcome", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNewEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        String employeeCreationStatus = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(employeeCreationStatus,HttpStatus.CREATED);
    }

    /**
     * @return employee list
     */
    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employeeList = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "employeeId") Long employeeId){
        Employee employee = employeeService.getEmployee(employeeId);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<String> updateEmployee(@PathVariable(name = "employeeId") Long employeeId, @Valid @RequestBody EmployeeDto employee){
        String updateStatus = employeeService.updateEmployee(employeeId,employee);
        return new ResponseEntity<>(updateStatus,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployee(@RequestParam String employeeName){
        List<Employee> employeeList = employeeService.searchEmployeeByName(employeeName);
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }
}
