import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent {
  loginData = {
    identifier: '', // Either email or adminName
    adminPassword: ''
  };

  constructor(private http: HttpClient, private router: Router) { }

  onLogin() {
    if (this.loginData.identifier && this.loginData.adminPassword) {
      const loginPayload = {
        email: this.loginData.identifier.includes('@') ? this.loginData.identifier : '',
        adminName: this.loginData.identifier.includes('@') ? '' : this.loginData.identifier,
        adminPassword: this.loginData.adminPassword
      };
      
      

      this.http.post<any>('http://localhost:8080/api/v1/admin/login', loginPayload)
        .subscribe(
          response => {
            if (response && response.token) {
              localStorage.setItem('adminToken', response.token);
              this.router.navigate(['/admin-dashboard']);
            } else {
              alert('Invalid login credentials');
            }
          },
          error => {
            alert('Login failed, please try again.');
          }
        );
    } else {
      alert('Please fill out both fields.');
    }
  }
}
