package br.com.edrocha.tasks.controllers;

import br.com.edrocha.tasks.dtos.tasks.CreateTaskDto;
import br.com.edrocha.tasks.dtos.tasks.TaskDto;
import br.com.edrocha.tasks.repositories.TaskRepository;
import br.com.edrocha.tasks.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    @GetMapping()
    public ResponseEntity<List<TaskDto>> findAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        var jwt = token.substring(7);
        return ResponseEntity.ok(
                taskService.findAll(jwt).stream().map(TaskDto::byEntity).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findbyId(@PathVariable Long id){
        return ResponseEntity.ok(TaskDto.byEntity(taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"))));
    }

    @PostMapping()
    public ResponseEntity<TaskDto> create(@RequestBody CreateTaskDto dto, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        var jwt = token.substring(7);
        var task = taskService.create(dto, jwt);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
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

