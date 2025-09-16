package com.example.ems.repository;

import com.example.ems.model.Leave;
import com.example.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {

    // Find leaves by Employee
    List<Leave> findByEmployee(Employee employee);

    // Find leaves by status
    List<Leave> findByStatus(String status);

    // Find leaves between two dates
    List<Leave> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
}
