import { ChangeDetectorRef, Component } from '@angular/core';
import { EmployeeService } from '../../../Service/employee.service';
import { MatTableModule } from '@angular/material/table';
import { AsyncPipe, CommonModule } from '@angular/common';
import {MatCardModule} from '@angular/material/card';
import { Observable, catchError, debounceTime, distinctUntilChanged, finalize, of, shareReplay, startWith, switchMap, tap } from 'rxjs';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [MatTableModule,AsyncPipe,MatCardModule,CommonModule,MatFormFieldModule,ReactiveFormsModule,MatIconModule,MatInputModule],
  
templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent {

  searchInput = new FormControl('');
  employees$:Observable<any[]> = of([]);
  displayedColumns: string[] = ['employeeId','employeeName','designation','createdDate','action'];
  dataSource = this.employees$;
  loading!:boolean;

  constructor(private employeeService:EmployeeService, private router:Router,){

  }

  ngOnInit():void{
    this.loadEmployeeList();
  }

  loadEmployeeList():void{
    this.employees$ = this.searchInput.valueChanges.pipe(
      startWith(''),
      debounceTime(300),
      distinctUntilChanged(),
      tap(()=>this.loading = true),
      switchMap(searchInput=>this.employeeService.getSearchResult(searchInput).pipe(
        catchError((error:any)=>{
          console.error('error: ',error);
          return of([])
        }),
        finalize(()=>this.loading = false)
      ))
    );
  }

  onView(employee: any): void {
    console.log('View:', employee);
    this.router.navigate(['/employee/view',employee.employeeId],{state:{employeeDetails:employee}})
  }
  
  onEdit(employee: any): void {
    console.log('Edit:', employee);
    this.router.navigate(['/employee/edit',employee.employeeId],{state:{employeeDetails:employee}})
  }
  
  onDelete(employee: any): void {
    console.log('Delete:', employee);
    // confirm dialog + API call
  }
  

}
