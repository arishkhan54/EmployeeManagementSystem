package com.example.ems.controller;

import com.example.ems.model.Payroll;
import com.example.ems.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payrolls")
@CrossOrigin(origins = "http://localhost:3000") // Adjust if frontend runs elsewhere
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @GetMapping
    public List<Payroll> getAllPayrolls() {
        return payrollService.getAllPayrolls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payroll> getPayrollById(@PathVariable Long id) {
        Optional<Payroll> payroll = payrollService.getPayrollById(id);
        return payroll.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Payroll generatePayroll(@RequestBody Payroll payroll) {
        return payrollService.generatePayroll(payroll);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payroll> updatePayroll(@PathVariable Long id, @RequestBody Payroll updatedPayroll) {
        Payroll payroll = payrollService.updatePayroll(id, updatedPayroll);
        return payroll != null ? ResponseEntity.ok(payroll) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Long id) {
        payrollService.deletePayroll(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Payroll> getPayrollByMonthAndYear(@RequestParam int month, @RequestParam int year) {
        return payrollService.getPayrollByMonthAndYear(month, year);
    }
}
