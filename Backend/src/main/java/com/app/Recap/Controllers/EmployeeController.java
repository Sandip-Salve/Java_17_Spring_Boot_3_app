package com.app.Recap.Controllers;

import com.app.Recap.DTOs.EmployeeDto;
import com.app.Recap.Entities.Employee;
import com.app.Recap.services.IEmployeeService;
import com.app.Recap.utilities.StatusResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/api")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/welcome")
    public String sayHi(){
        return "Say, HI";
    }

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
    public ResponseEntity<StatusResponse> updateEmployee(@PathVariable(name = "employeeId") Long employeeId, @Valid @RequestBody EmployeeDto employee){
        String updateStatus = employeeService.updateEmployee(employeeId,employee);
        return new ResponseEntity<>(new StatusResponse(updateStatus),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchEmployee(@RequestParam String employeeName){
        List<Employee> employeeList = employeeService.searchEmployeeByName(employeeName);
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }

    @GetMapping("/getPaginatedEmployees")
    public ResponseEntity<Page<Employee>> getPaginatedEmployees(@RequestParam("pageNumber")Integer pageNumber,
                                                                @RequestParam("pageSize") Integer pageSize,
                                                                @RequestParam("sortBy")String sortBy,
                                                                @RequestParam("sortingDirection") String sortingDirection){
        Pageable pageableObj = PageRequest.of(pageNumber,pageSize, Sort.by(sortingDirection.equals("DESC")?Sort.Direction.DESC : Sort.Direction.ASC,sortBy));
        Page<Employee> employeePage = employeeService.getPaginatedData(pageableObj);
        return new ResponseEntity<>(employeePage,HttpStatus.OK);
    }
}
