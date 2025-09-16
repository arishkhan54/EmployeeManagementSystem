package com.example.ems.controller;

import com.example.ems.model.Attendance;
import com.example.ems.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:3000") // Adjust if frontend runs elsewhere
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping
    public List<Attendance> getAllAttendanceRecords() {
        return attendanceService.getAllAttendanceRecords();
    }
    @GetMapping("/attendance")
    public ResponseEntity<List<Attendance>> getAttendanceByMonth(@RequestParam(required = false, defaultValue = "1") int month) {
        return ResponseEntity.ok(attendanceService.getAttendanceByMonth(month));
}


    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable Long id) {
        Optional<Attendance> attendance = attendanceService.getAttendanceById(id);
        return attendance.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Attendance addAttendanceRecord(@RequestBody Attendance attendance) {
        return attendanceService.addAttendance(attendance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable Long id, @RequestBody Attendance updatedAttendance) {
        Attendance attendance = attendanceService.updateAttendance(id, updatedAttendance);
        return attendance != null ? ResponseEntity.ok(attendance) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }
}
