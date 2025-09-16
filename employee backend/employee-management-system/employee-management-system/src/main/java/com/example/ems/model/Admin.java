package com.example.ems.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admin")
public class Admin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminID;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public enum Role { SuperAdmin, HRAdmin }

    // No-args constructor
    public Admin() {}

    // All-args constructor (optional)
    public Admin(String name, String email, String password, Role role) {
      this.name = name;
      this.email = email;
      this.password = password;
      this.role = role;
    }

    // ——— Manually added getters ———
    public Long getAdminID() {
      return adminID;
    }
    public String getName() {
      return name;
    }
    public String getEmail() {
      return email;
    }
    public String getPassword() {
      return password;
    }
    public Role getRole() {
      return role;
    }

    // And setters if needed…
    public void setName(String name) {
      this.name = name;
    }
    public void setEmail(String email) {
      this.email = email;
    }
    public void setPassword(String password) {
      this.password = password;
    }
    public void setRole(Role role) {
      this.role = role;
    }
}
