package com.example.ems.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "payrolls")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double salaryAmount;
    private LocalDate payDate;
    private int month;
    private int year;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)  // Foreign key column in payrolls table
    @JsonIgnore
    private Employee employee;

    public Payroll() {}

    public Payroll(Double salaryAmount, LocalDate payDate, int month, int year, Employee employee) {
        this.salaryAmount = salaryAmount;
        this.payDate = payDate;
        this.month = month;
        this.year = year;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(Double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
