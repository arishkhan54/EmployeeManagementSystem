package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Attendance;
import com.example.demo.repository.AttendanceRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;
    
    // Get all attendance records
    @GetMapping("/attendance")
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
    
    // Create attendance record
    @PostMapping("/attendance")
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        attendance.setDate(LocalDate.now());
        attendance.setTimeIn(LocalDateTime.now());
        return attendanceRepository.save(attendance);
    }
    
    // Get attendance by employee ID
    @GetMapping("/attendance/employee/{employeeId}")
    public List<Attendance> getAttendanceByEmployeeId(@PathVariable Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }
    
    // Mark time out
    @PutMapping("/attendance/{id}/timeout")
    public ResponseEntity<Attendance> markTimeOut(@PathVariable Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));
        
        attendance.setTimeOut(LocalDateTime.now());
        Attendance updatedAttendance = attendanceRepository.save(attendance);
        return ResponseEntity.ok(updatedAttendance);
    }
    
    // Delete attendance record
    @DeleteMapping("/attendance/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAttendance(@PathVariable Long id) {
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));
        
        attendanceRepository.delete(attendance);
        
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
