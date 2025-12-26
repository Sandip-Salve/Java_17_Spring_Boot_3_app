import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseURL:string = 'http://localhost:8081/employee/api';
  constructor(private httpClient:HttpClient) { }

  getEmployeeList():Observable<any[]>{
    return this.httpClient.get<any[]>(`${this.baseURL}/list`);
  }

  getSearchResult(value:any):Observable<any[]>{
    return this.httpClient.get<any[]>(`${this.baseURL}/search?employeeName=${value}`);
  }
}
