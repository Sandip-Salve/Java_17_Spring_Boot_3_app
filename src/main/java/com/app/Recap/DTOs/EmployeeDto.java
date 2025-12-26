package com.app.Recap.DTOs;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class EmployeeDto {

    @NotNull(message = "Employee Name must not be null!")
    private String employeeName;

    @NotNull(message = "Designation must not be null!")
    private String designation;

    private Date createdDate;

    private Date updatedDate;
}
