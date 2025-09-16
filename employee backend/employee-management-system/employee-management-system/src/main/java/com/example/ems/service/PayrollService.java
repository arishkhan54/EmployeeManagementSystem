package com.example.ems.service;

import com.example.ems.model.Payroll;
import com.example.ems.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    public Optional<Payroll> getPayrollById(Long id) {
        return payrollRepository.findById(id);
    }

    public Payroll generatePayroll(Payroll payroll) {
        return payrollRepository.save(payroll);
    }

    public Payroll updatePayroll(Long id, Payroll updatedPayroll) {
        if (payrollRepository.existsById(id)) {
            updatedPayroll.setId(id);
            return payrollRepository.save(updatedPayroll);
        }
        return null;
    }

    public void deletePayroll(Long id) {
        payrollRepository.deleteById(id);
    }

    public List<Payroll> getPayrollByMonthAndYear(int month, int year) {
        return payrollRepository.findByMonthAndYear(month, year);
    }
}
