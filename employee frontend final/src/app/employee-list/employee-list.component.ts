import { Component } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent {

  employees: Employee[] = [];
  EnteredID!: number;

  constructor(
    private employeeService: EmployeeService, 
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getEmployees();
  }

  goToEmployee() {
    console.log(this.EnteredID); 
    this.router.navigate(['details-of-employee', this.EnteredID]);
  }

  getEmployees() {
    this.employeeService.getEmployeesList().subscribe({
      next: (data) => {
        this.employees = data;
      },
      error: (error) => {
        console.error('Error fetching employees:', error);
        // You can add user notification here
      }
    });
  }

  updateEmployee(id: number) {
    this.router.navigate(['updating-by-id', id]);
  }

  deleteEmployee(id: number) {
    if (confirm("Are you sure to delete Employee ID: " + id)) {
      this.employeeService.deleteEmployee(id).subscribe({
        next: (data) => {
          console.log(data);
          this.getEmployees();
        },
        error: (error) => {
          console.error('Error deleting employee:', error);
          // You can add user notification here
        }
      });
    }
  }

  detailsOfEmployee(id: number) {
    this.router.navigate(['details-of-employee', id]);
  }
}
