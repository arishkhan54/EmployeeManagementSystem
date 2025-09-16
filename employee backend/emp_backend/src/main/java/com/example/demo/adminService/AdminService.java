package com.example.demo.adminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.adminModel.adminModel;
import com.example.demo.adminRepository.adminRepository;

@Service
public class AdminService {

    @Autowired
    private adminRepository adminRepository;

    // Validate admin login credentials
    public boolean validateAdminLogin(String identifier, String password) {
        try {
            adminModel admin = adminRepository.findByEmailOrAdminName(identifier, identifier);

            if (admin != null && admin.getAdminPassword() != null && admin.getAdminPassword().equals(password)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error in validateAdminLogin: " + e.getMessage());
            return false;
        }
    }

    // Register a new admin
    public adminModel registerAdmin(adminModel admin) {
        if (adminRepository.findByEmail(admin.getEmail()) != null) {
            throw new RuntimeException("Email already registered");
        }
        return adminRepository.save(admin);
    }
}
