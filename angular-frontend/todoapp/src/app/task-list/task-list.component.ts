import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Task } from '../task';
import { TaskService } from '../task.service'

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit{

  tasks?: Task[];

  constructor(private taskService: TaskService,
    private router: Router) {}

  ngOnInit() {
   this.getTasks();
  }

  private getTasks(){
    this.taskService.getTasksList().subscribe(data => {
      this.tasks = data;
      console.log("Task: ",this.tasks)
    });
  }

  updateTask(id:number){
    console.log("id: " , id)
    this.router.navigate(['update-task', id]); 
  }

  deleteTask(id:number){
    this.taskService.deleteTask(id).subscribe(data => {
      console.log(data);
      this.getTasks()
    });
  }

  goToCreateTask(){
    this.router.navigate(['create-task']);
  }
}
