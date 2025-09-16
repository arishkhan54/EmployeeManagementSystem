package com.example.ems.service;

import com.example.ems.model.Attendance;
import com.example.ems.model.Employee;
import com.example.ems.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Get all attendance records
    public List<Attendance> getAllAttendanceRecords() {
        return attendanceRepository.findAll();
    }

    // Get attendance record by ID
    public Optional<Attendance> getAttendanceById(Long id) {
        return attendanceRepository.findById(id);
    }

    // Get attendance records for a specific employee
    public List<Attendance> getAttendanceByEmployee(Employee employee) {
        return attendanceRepository.findByEmployee(employee);
    }

    // Add a new attendance record
    public Attendance addAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    // Update an existing attendance record
    public Attendance updateAttendance(Long id, Attendance updatedAttendance) {
        Optional<Attendance> existingRecord = attendanceRepository.findById(id);
        if (existingRecord.isPresent()) {
            Attendance attendance = existingRecord.get();
            attendance.setDate(updatedAttendance.getDate());
            attendance.setStatus(updatedAttendance.getStatus());
            return attendanceRepository.save(attendance);
        }
        return null;
    }
    public List<Attendance> getAttendanceByMonth(int month) {
        return attendanceRepository.findByMonth(month);
    }

    // Delete an attendance record
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
}
