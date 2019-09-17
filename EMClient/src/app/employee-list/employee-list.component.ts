import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { EmployeeModule } from '../employee/employee.module';
import { EmployeeService } from '../employee.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees:Observable<EmployeeModule[]>;

  constructor(private employeeservice:EmployeeService, private route:Router) { }

  ngOnInit() {
  }

  reloadData(){
    this.employees = this.employeeservice.getEmployeesList()
  }

  deleteEmployee(id:Number){
    this.employeeservice.deleteEmployee(id).subscribe(data =>{
      console.log(data);
      this.reloadData();
    },
    error => console.error()

    );
  }

  employeeDetails(id:Number){
    this.route.navigate(['/empdetails',id]);
  }

}
