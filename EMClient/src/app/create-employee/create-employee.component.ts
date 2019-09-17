import { Component, OnInit } from '@angular/core';
import { EmployeeModule } from '../employee/employee.module';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  employee:EmployeeModule = new EmployeeModule();
  submited:boolean = false;
  firstname:string;

  constructor(private employeeservice:EmployeeService, private router:Router) { }

  ngOnInit() {
  }

  saveEmployee(){
    this.submited= true;
    this.employeeservice.createEmployee(this.employee).subscribe(data => 
      console.log(data),
      error => console.error()
      );
      this.employee = new EmployeeModule();
      this.listView();
  }

  listView(){
    this.router.navigate(['/employees']);
  }

}
