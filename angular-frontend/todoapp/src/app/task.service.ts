import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'
import { Injectable } from '@angular/core';
import { Task } from './task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  private baseURL = "http://localhost:3000/api/tasks";

  constructor(private httpClient: HttpClient) { }

  getTasksList(): Observable<Task[]>{
    return this.httpClient.get<Task[]>(`${this.baseURL}`);
  }

  createTask(task: Task): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, { name: 'diego', description: 'oooi' });
  }

  getTaskById(id: number): Observable<Task[]>{
    return this.httpClient.get<Task[]>(`${this.baseURL}/${id}`);
  }

  updateTask(task: Task): Observable<Object>{
    console.log("Task:", task)
    return this.httpClient.put(`${this.baseURL}`, task);
  }

  deleteTask(id: number): Observable<Object>{
    console.log("Task:", id)
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
