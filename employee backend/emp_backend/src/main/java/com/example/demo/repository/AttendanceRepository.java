package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    
    // Find attendance records by employee ID
    List<Attendance> findByEmployeeId(Long employeeId);
    
    // Find attendance records by date
    List<Attendance> findByDate(java.time.LocalDate date);
    
    // Find attendance records by employee ID and date
    List<Attendance> findByEmployeeIdAndDate(Long employeeId, java.time.LocalDate date);
}
