import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../../../Service/employee.service';
import { EMPTY, catchError } from 'rxjs';

@Component({
  selector: 'app-emloyee-regi',
  standalone: true,
  imports: [ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,CommonModule],
  templateUrl: './emloyee-regi.component.html',
  styleUrl: './emloyee-regi.component.css'
})
export class EmloyeeRegiComponent {

  employeeForm!: FormGroup;
  mode!: 'create' | 'edit' | 'view';

  constructor(private fb:FormBuilder, protected router:Router,
    private employeeService:EmployeeService){

  }

  ngOnInit(): void {
    this.initForm();
    this.detectMode();
    if(this.mode==='edit' || this.mode==='view'){
      this.loadEmployee();
    }
  }

  private initForm(): void {
    this.employeeForm = this.fb.group({
      employeeId: [{ value: null, disabled: true }],
      employeeName: ['', [Validators.required, Validators.minLength(3)]],
      designation: ['', Validators.required]
    });
  }

  private detectMode(): void {
    const url = this.router.url;

    if (url.includes('view')) {
      this.mode = 'view';
      this.employeeForm.disable();
    } else if (url.includes('edit')) {
      this.mode = 'edit';
    } else {
      this.mode = 'create';
    }
  }

  loadEmployee():void{
    const employee = history.state?.employeeDetails;
    if(employee){
      this.employeeForm.patchValue(employee);
    }else{
      alert('needed')
    }
  }

  onSubmit(): void {
    if (this.employeeForm.invalid) {
      this.employeeForm.markAllAsTouched();
      return;
    }

    const payload = this.employeeForm.getRawValue();

    console.log('Employee payload:', payload);

    if(this.mode==='edit'){
      const employee = history.state?.employeeDetails;
      if(employee){
        const id = employee.employeeId;
        this.employeeService.updateEmployeeDetails(id,payload).pipe(
          catchError((error)=>{
            console.error('Error: ',error);
            return EMPTY;
          })
        ).subscribe(()=>{
          this.router.navigate(['/employee/list']);
        })
      }
      
    }else {

    }

  }
}
