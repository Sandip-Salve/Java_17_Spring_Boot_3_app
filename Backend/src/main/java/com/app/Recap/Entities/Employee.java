package com.app.Recap.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employee_crud")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long employeeId;

    @Column(name = "emp_name")
    private String employeeName;

    @Column(name = "emp_designation")
    private String designation;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "createdAt")
    private Date createdDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "updatedAt")
    private Date updatedDate;
}
