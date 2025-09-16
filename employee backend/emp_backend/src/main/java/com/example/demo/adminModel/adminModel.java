package com.example.demo.adminModel;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class adminModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminID;

    @Column(name = "adminName")
    private String adminName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "adminPassword")
    private String adminPassword;

    public adminModel() {
    }

    public adminModel(String adminName, String email, String adminPassword) {
        this.adminName = adminName;
        this.email = email;
        this.adminPassword = adminPassword;
    }

    public long getAdminID() {
        return adminID;
    }

    public void setAdminID(long adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
