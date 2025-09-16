package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.adminModel.adminModel;
import com.example.demo.adminRepository.adminRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private adminRepository adminRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create default admin if it doesn't exist
        if (adminRepository.findByEmail("admin@example.com") == null) {
            adminModel defaultAdmin = new adminModel();
            defaultAdmin.setAdminName("admin");
            defaultAdmin.setEmail("admin@example.com");
            defaultAdmin.setAdminPassword("admin123");
            
            adminRepository.save(defaultAdmin);
            System.out.println("Default admin created successfully!");
            System.out.println("Username: admin");
            System.out.println("Password: admin123");
            System.out.println("Email: admin@example.com");
        } else {
            System.out.println("Default admin already exists!");
        }
    }
}
