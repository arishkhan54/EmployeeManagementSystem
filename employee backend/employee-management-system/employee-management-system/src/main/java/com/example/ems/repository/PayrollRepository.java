package com.example.ems.repository;

import com.example.ems.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    // Fixed method: Ensure month and year exist in Payroll entity
    List<Payroll> findByMonthAndYear(int month, int year);

    // Alternative method using payDate instead of month and year
    @Query("SELECT p FROM Payroll p WHERE FUNCTION('MONTH', p.payDate) = :month AND FUNCTION('YEAR', p.payDate) = :year")
    List<Payroll> findByMonthAndYearUsingDate(@Param("month") int month, @Param("year") int year);
}
