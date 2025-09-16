// admin-register.component.ts - Updated with validation
import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../admin.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-admin-register',
  templateUrl: './admin-register.component.html',
  //styleUrls: ['./admin-register.component.css']
})
export class AdminRegisterComponent {
  registerData = {
    adminName: '',
    email: '',
    adminPassword: ''
  };

  successMessage = '';
  errorMessage = '';
  isSubmitting = false;
  
  @ViewChild('registerForm') registerForm!: NgForm;

  constructor(private adminService: AdminService, private router: Router) {}

  onRegister() {
    // Form validation
    if (!this.registerData.adminName || !this.registerData.email || !this.registerData.adminPassword) {
      this.errorMessage = 'Please fill all required fields';
      return;
    }

    // Email validation
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailRegex.test(this.registerData.email)) {
      this.errorMessage = 'Please enter a valid email address';
      return;
    }

    // Password validation (minimum 6 characters)
    if (this.registerData.adminPassword.length < 6) {
      this.errorMessage = 'Password must be at least 6 characters long';
      return;
    }

    this.isSubmitting = true;
    this.errorMessage = '';
    
    this.adminService.registerAdmin(this.registerData).subscribe({
      next: (response: any) => {
        this.successMessage = "Admin registered successfully!";
        this.registerData = { adminName: '', email: '', adminPassword: '' };
        this.isSubmitting = false;
        
        // Redirect to login page after 2 seconds
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000);
      },
      error: (error: any) => {
        console.error('Registration failed:', error);
        this.errorMessage = error.message || 'Registration failed. Please try again.';
        this.isSubmitting = false;
      }
    });
  }
}