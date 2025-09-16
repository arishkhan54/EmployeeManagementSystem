package com.example.demo.adminRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.adminModel.adminModel;

@Repository
public interface adminRepository extends JpaRepository<adminModel, Long> {

    // Find by admin name
    adminModel findByAdminName(String adminName);

    // Find by either email or admin name
    adminModel findByEmailOrAdminName(String email, String adminName);

    // Find by email
    adminModel findByEmail(String email);
}
