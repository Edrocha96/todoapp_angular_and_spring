import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateTaskComponent } from './create-task/create-task.component';
import { TaskListComponent } from './task-list/task-list.component';
import { TaskUpdateComponent } from './task-update/task-update.component';


const routes: Routes = [
  {path: 'tasks', component: TaskListComponent},
  {path: 'create-task', component: CreateTaskComponent},
  {path: '', redirectTo: 'tasks', pathMatch: 'full'},
  {path:'update-task/:id', component: TaskUpdateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
