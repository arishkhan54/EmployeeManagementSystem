package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "employee_id")
    private Long employeeId;
    
    @Column(name = "date")
    private LocalDate date;
    
    @Column(name = "time_in")
    private LocalDateTime timeIn;
    
    @Column(name = "time_out")
    private LocalDateTime timeOut;
    
    @Column(name = "status")
    private String status; // Present, Absent, Late, etc.
    
    // Default constructor
    public Attendance() {}
    
    // Parameterized constructor
    public Attendance(Long employeeId, LocalDate date, LocalDateTime timeIn, LocalDateTime timeOut, String status) {
        this.employeeId = employeeId;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.status = status;
    }
    
    // Getters and setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public LocalDateTime getTimeIn() {
        return timeIn;
    }
    
    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }
    
    public LocalDateTime getTimeOut() {
        return timeOut;
    }
    
    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
