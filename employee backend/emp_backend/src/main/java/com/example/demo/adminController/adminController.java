package com.example.demo.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.adminModel.AdminLoginRequest;
import com.example.demo.adminModel.adminModel;
import com.example.demo.adminService.AdminService;

@RestController
@RequestMapping("/api/v1/")
public class adminController {
    @Autowired
    private AdminService adminService;

    // Admin Login endpoint
    @PostMapping("/admin/login")
    public ResponseEntity<String> login(@RequestBody AdminLoginRequest loginRequest) {
        try {
            String identifier = null;
            
            // Check if email is provided and not empty
            if (loginRequest.getEmail() != null && !loginRequest.getEmail().trim().isEmpty()) {
                identifier = loginRequest.getEmail();
            }
            // If email is not provided, check if adminName is provided
            else if (loginRequest.getAdminName() != null && !loginRequest.getAdminName().trim().isEmpty()) {
                identifier = loginRequest.getAdminName();
            }
            
            // If neither email nor adminName is provided, return error
            if (identifier == null) {
                return ResponseEntity.status(400).body("Email or Admin Name is required");
            }
            
            // Check if password is provided
            if (loginRequest.getAdminPassword() == null || loginRequest.getAdminPassword().trim().isEmpty()) {
                return ResponseEntity.status(400).body("Password is required");
            }

            boolean isValid = adminService.validateAdminLogin(identifier, loginRequest.getAdminPassword());

            if (isValid) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(401).body("Invalid login credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }

    // Admin Register endpoint
    @PostMapping("/admin/register")
    public ResponseEntity<String> register(@RequestBody adminModel admin) {
        try {
            adminService.registerAdmin(admin);
            return ResponseEntity.ok("Admin registered successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // Create default admin for testing
    @PostMapping("/admin/create-default")
    public ResponseEntity<String> createDefaultAdmin() {
        try {
            adminModel defaultAdmin = new adminModel();
            defaultAdmin.setAdminName("admin");
            defaultAdmin.setEmail("admin@example.com");
            defaultAdmin.setAdminPassword("admin123");
            
            adminService.registerAdmin(defaultAdmin);
            return ResponseEntity.ok("Default admin created successfully! Username: admin, Password: admin123");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Default admin already exists: " + e.getMessage());
        }
    }
}
