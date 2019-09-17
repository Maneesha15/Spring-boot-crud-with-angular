import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { observable, Observable } from 'rxjs';
import { EmployeeModule } from './employee/employee.module';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
private baseurl="http://localhost:8080/employees";

  constructor(private http:HttpClient) { }

  getEmployeesList():Observable<any>{
    return this.http.get(`${this.baseurl}`)
  }

  deleteEmployee(id:Number):Observable<object>{
    return this.http.delete(`${this.baseurl}`)
  }

  createEmployee(employee:EmployeeModule):Observable<object>{
    return this.http.post(`${this.baseurl}`,employee)

  }

}
