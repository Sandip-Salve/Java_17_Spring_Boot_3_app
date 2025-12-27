import { Routes } from '@angular/router';
import { EmployeeListComponent } from './components/Employee/employee-list/employee-list.component';
import { EmloyeeRegiComponent } from './components/Employee/emloyee-regi/emloyee-regi.component';

export const routes: Routes = [
    {path: '',redirectTo:'/employee/list',pathMatch:'full'},
    // {path:'employees',component:EmployeeListComponent},
    {
        path: 'employee',
        children: [
            {path:'list', component: EmployeeListComponent},
            {path:'new', component: EmloyeeRegiComponent},
            {path:'view/:id', component: EmloyeeRegiComponent},
            {path:'edit/:id', component: EmloyeeRegiComponent},
        ]
    }
];
