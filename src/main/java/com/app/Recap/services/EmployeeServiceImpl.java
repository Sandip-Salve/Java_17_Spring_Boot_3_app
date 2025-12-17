package com.app.Recap.services;

import com.app.Recap.DTOs.EmployeeDto;
import com.app.Recap.Entities.Employee;
import com.app.Recap.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Employee Service implementation
 */
@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     *
     * @param employeeDto new Employee creation request
     * @return "status of new Employee creation"
     */
    @Override
    @CacheEvict(value = "employees", allEntries = true)
    public String saveEmployee(EmployeeDto employeeDto) {
        if(employeeDto!=null){
            Employee newEmployee = new Employee();
            newEmployee.setEmployeeName(employeeDto.getEmployeeName());
            newEmployee.setDesignation(employeeDto.getDesignation());
            newEmployee.setCreatedDate(new Date());
            newEmployee = employeeRepository.save(newEmployee);
            return "Successfully created new employee with EmployeeID: "+ newEmployee.getEmployeeId();
        }
        return "Failed to create new Employee";
    }

    @Override
    @Cacheable("employees")
    public List<Employee> getAllEmployees() {
        System.out.println("Inside Service: ");
        long start = System.currentTimeMillis();
        List<Employee> employeeList = employeeRepository.findAll();
        System.out.println("TIME: "+start);
        return employeeList;
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    @Override
    public String deleteEmployee(Long employeeId) {
        return "";
    }

    @Override
    public List<Employee> searchEmployeeByName(String searchInput) {
        return employeeRepository.findByEmployeeNameContainingIgnoreCase(searchInput);
    }

    @Override
    @CacheEvict(value = "employees", allEntries = true)
    public String updateEmployee(Long employeeId, EmployeeDto employee) {
        Employee existingEmployee = employeeRepository.findById(employeeId).orElse(null);
        if(existingEmployee!=null){
            existingEmployee.setUpdatedDate(new Date());
            existingEmployee.setEmployeeName(employee.getEmployeeName());
            existingEmployee.setDesignation(employee.getDesignation());
            employeeRepository.save(existingEmployee);
            return "Successfully updated";
        }
        return "Failed to update employee with ID: "+employeeId;
    }
}
