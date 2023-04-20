package br.com.edrocha.tasks.controllers;

import br.com.edrocha.tasks.dtos.tasks.CreateTaskDto;
import br.com.edrocha.tasks.dtos.tasks.TaskDto;
import br.com.edrocha.tasks.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping()
    public ResponseEntity<List<TaskDto>> findAll(){
        return ResponseEntity.ok(
                taskRepository.findAll().stream().map(TaskDto::byEntity).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findbyId(@PathVariable Long id){
        return ResponseEntity.ok(TaskDto.byEntity(taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"))));
    }

    @PostMapping()
    public ResponseEntity<TaskDto> create(@RequestBody CreateTaskDto dto) {
        return ResponseEntity.ok(TaskDto.byEntity(this.taskRepository.save(dto.toEntity())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.taskRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CreateTaskDto dto) {
        var task = dto.toEntity();
        task.setId(id);
        this.taskRepository.save(task);
        return ResponseEntity.ok().build();
    }
}

