package br.com.edrocha.tasks.services;

import br.com.edrocha.tasks.dtos.tasks.CreateTaskDto;
import br.com.edrocha.tasks.dtos.tasks.TaskDto;
import br.com.edrocha.tasks.entities.TaskEntity;
import br.com.edrocha.tasks.repositories.TaskRepository;
import br.com.edrocha.tasks.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskDto create(CreateTaskDto dto, String accessToken) {
        var email = jwtService.extractUsername(accessToken);
        var user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }

        var task = TaskEntity
                .builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .user(user)
                .build();

        taskRepository.save(task);

        return TaskDto.byEntity(task);
    }

    public List<TaskEntity> findAll(String accessToken) {
        var email = jwtService.extractUsername(accessToken);
        return taskRepository.findAllByUserEmail(email);
    }
}
