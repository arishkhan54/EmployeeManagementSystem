import { Component } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent {

  employee: Employee = new Employee();

  constructor(
    private employeeService: EmployeeService,
    private router: Router,
  ) { }

  saveEmployee() {
    this.employeeService.addEmployee(this.employee).subscribe({
      next: (data) => {
        console.log(data);
        this.goToEmployeeList();
      },
      error: (error) => {
        console.error('Error adding employee:', error);
        // You can add user notification here
      }
    });
  }

  goToEmployeeList() {
    this.router.navigate(['/show-all-employees']);
  }

  ngOnInit(): void { }

  onSubmit() {
    console.log(this.employee);
    this.saveEmployee();
  }
}









