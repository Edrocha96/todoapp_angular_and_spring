import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Task } from '../task';
import { TaskService } from '../task.service';

@Component({
  selector: 'app-task-update',
  templateUrl: './task-update.component.html',
  styleUrls: ['./task-update.component.css']
})
export class TaskUpdateComponent implements OnInit{

  id: any;
  task: any;
  
  constructor(private taskService: TaskService,
    private route: ActivatedRoute,
    private router: Router){

  }

  ngOnInit(): void {
    this.task = new Task();
    this.id = this.route.snapshot.params['id'];

    this.taskService.getTaskById(this.id).subscribe(data =>{
      console.log(data);
      this.task = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.taskService.updateTask(this.task).subscribe(data =>{
      this.goToTaskList();
    }, error => console.log("Erro ao atualizar:", error))
    console.log(this.task);

  }

  goToTaskList(){
    this.router.navigate(['tasks']);
  }
}
