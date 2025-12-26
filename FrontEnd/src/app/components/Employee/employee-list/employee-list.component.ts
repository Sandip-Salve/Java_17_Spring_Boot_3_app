import { Component } from '@angular/core';
import { EmployeeService } from '../../../Service/employee.service';
import { MatTableModule } from '@angular/material/table';
import { AsyncPipe, CommonModule } from '@angular/common';
import {MatCardModule} from '@angular/material/card';
import { Observable, of } from 'rxjs';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [MatTableModule,AsyncPipe,MatCardModule,CommonModule],
  
templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent {

  employees$:Observable<any[]> = of([]);
  displayedColumns: string[] = ['employeeId','employeeName','designation','createdDate'];
  dataSource = this.employees$;

  constructor(private employeeService:EmployeeService){

  }

  ngOnInit():void{
    this.loadEmployeeList();
  }

  loadEmployeeList():void{
    this.employees$ = of([]);
  }
}
