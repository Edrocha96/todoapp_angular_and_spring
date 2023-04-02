package br.com.edrocha.todoapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.edrocha.todoapp.req.TaskReq;
import br.com.edrocha.todoapp.resp.TaskResp;
import br.com.edrocha.todoapp.service.TaskService;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/to-do-app")
public class TaskController {
	
	private TaskService toDoAppService;
	
	public TaskController(TaskService toDoAppService) {
		this.toDoAppService = toDoAppService;
	}

	@GetMapping("/all-registers")
	public ResponseEntity<List<TaskResp>> findAll(){
		System.out.println("Chegou");
		return ResponseEntity.ok(toDoAppService.findAll());
	}
	

	@GetMapping("/by-id-registers/{taskId}")
	public ResponseEntity<TaskResp> findbyId(@PathVariable String taskId){
		return ResponseEntity.ok(toDoAppService.findById(taskId));
	}
	
	@PostMapping("/save-task")
	public ResponseEntity<Void> saveTask(@RequestBody TaskReq req) {
		toDoAppService.save(req);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/delete-task")
	public ResponseEntity<Void> deleteTask(@RequestBody String id) {
		toDoAppService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/update-task")
	public ResponseEntity<Void> updateTask(@RequestBody TaskReq req) {
		System.out.println("Chegou Atualizar");
		toDoAppService.update(req);
		return ResponseEntity.ok().build();
	}

}
