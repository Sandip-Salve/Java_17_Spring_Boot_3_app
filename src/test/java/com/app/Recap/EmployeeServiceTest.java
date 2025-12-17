package com.app.Recap;

import com.app.Recap.DTOs.EmployeeDto;
import com.app.Recap.Entities.Employee;
import com.app.Recap.Repositories.EmployeeRepository;
import com.app.Recap.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void shouldSaveEmployee(){
        EmployeeDto empDto = new EmployeeDto("Sandip","Dev",null,null);

        Employee empObject = new Employee(1L,"Sandip","Dev",null,null);

        when(employeeRepository.save(any(Employee.class))).thenReturn(empObject); // Expected behavior

        String result = employeeService.saveEmployee(empDto); // Testing

        verify(employeeRepository).save(any(Employee.class)); // verifying

        assertEquals("Successfully created new employee with EmployeeID: 1",result);
    }
}
