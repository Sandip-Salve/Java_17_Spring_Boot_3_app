package com.app.Recap.services;

import com.app.Recap.DTOs.EmployeeDto;
import com.app.Recap.Entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IEmployeeService {

    String saveEmployee(EmployeeDto employeeDto);

    List<Employee> getAllEmployees();

    Employee getEmployee(Long employeeId);

    String deleteEmployee(Long employeeId);

    List<Employee> searchEmployeeByName(String searchInput);

    String updateEmployee(Long employeeId, EmployeeDto employee);
}
