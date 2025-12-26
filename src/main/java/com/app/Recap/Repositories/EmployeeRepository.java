package com.app.Recap.Repositories;

import com.app.Recap.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee> findByEmployeeNameContainingIgnoreCase(String employeeName);
}
