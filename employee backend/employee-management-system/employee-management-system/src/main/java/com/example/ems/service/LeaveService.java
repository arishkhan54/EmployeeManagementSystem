package com.example.ems.service;

import com.example.ems.model.Leave;
import com.example.ems.model.Employee;
import com.example.ems.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public Optional<Leave> getLeaveById(Long id) {
        return leaveRepository.findById(id);
    }

    public List<Leave> getLeavesByEmployee(Employee employee) {
        return leaveRepository.findByEmployee(employee);
    }

    public List<Leave> getLeavesByStatus(String status) {
        return leaveRepository.findByStatus(status);
    }

    public List<Leave> getLeavesBetweenDates(LocalDate startDate, LocalDate endDate) {
        return leaveRepository.findByStartDateBetween(startDate, endDate);
    }

    public Leave requestLeave(Leave leave) {
        leave.setStatus("Pending"); // Default status when a new leave request is created
        return leaveRepository.save(leave);
    }

   
    public Leave updateLeave(Long id, Leave updatedLeave) {
        Optional<Leave> optionalLeave = leaveRepository.findById(id);
        if (optionalLeave.isPresent()) {
            Leave leave = optionalLeave.get();
            leave.setStartDate(updatedLeave.getStartDate());
            leave.setEndDate(updatedLeave.getEndDate());
            leave.setReason(updatedLeave.getReason());
            leave.setStatus(updatedLeave.getStatus());
            return leaveRepository.save(leave);
        }
        return null; // If leave not found, return null
    }

    public Leave updateLeaveStatus(Long leaveId, String status) {
        Optional<Leave> optionalLeave = leaveRepository.findById(leaveId);
        if (optionalLeave.isPresent()) {
            Leave leave = optionalLeave.get();
            leave.setStatus(status);
            return leaveRepository.save(leave);
        }
        return null;
    }

    public void deleteLeave(Long id) {
        leaveRepository.deleteById(id);
    }
}
