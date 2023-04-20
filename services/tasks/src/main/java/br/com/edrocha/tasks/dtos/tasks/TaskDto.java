package br.com.edrocha.tasks.dtos.tasks;

import br.com.edrocha.tasks.entities.TaskEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;

    public TaskDto(Long id, String name, String description, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public static TaskDto byEntity(TaskEntity task) {
        return new TaskDto(task.getId(), task.getName(), task.getDescription(), task.getCreatedAt());
    }
}
