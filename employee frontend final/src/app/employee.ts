import { DatePipe } from '@angular/common';

export class Employee {
    id!: number;
    fname!: string;
    lname!: string;
    email!: string;
    salary!: number;
    department!: string;
    designation!: string;
    joiningDate!: string;
  
  constructor() {
    this.department = '';
    this.designation = '';
    this.salary = 0;
  }
}
