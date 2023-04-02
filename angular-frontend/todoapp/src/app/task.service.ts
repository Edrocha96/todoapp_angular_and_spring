import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'
import { Injectable } from '@angular/core';
import { Task } from './task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private baseURL = "http://localhost:8080/to-do-app";

  private endpointGetAllTasks = "/all-registers";

  private endpointCreateTask = "/save-task";

  private endpointGetTaskById = "/by-id-registers";

  private endpointUpdateTask = "/update-task";

  private endpointDeleteTask = "/delete-task";

  constructor(private httpClient: HttpClient) { }

  getTasksList(): Observable<Task[]>{
    return this.httpClient.get<Task[]>(`${this.baseURL + this.endpointGetAllTasks}`);
  }

  createTask(task: Task): Observable<Object>{
    return this.httpClient.post(`${this.baseURL + this.endpointCreateTask}`, task);
  }

  getTaskById(id: number): Observable<Task[]>{
    return this.httpClient.get<Task[]>(`${this.baseURL + this.endpointGetTaskById}/${id}`);
  }

  updateTask(task: Task): Observable<Object>{
    console.log("Task:", task)
    return this.httpClient.post(`${this.baseURL + this.endpointUpdateTask}`, task);
  }

  deleteTask(id: number): Observable<Object>{
    console.log("Task:", id)
    return this.httpClient.post(`${this.baseURL + this.endpointDeleteTask}`, id);
  }
}
