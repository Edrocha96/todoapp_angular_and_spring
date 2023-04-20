package br.com.edrocha.tasks.dtos.tasks;

import br.com.edrocha.tasks.entities.TaskEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTaskDto {
    private String name;
    private String description;

    public TaskEntity toEntity() {
        return new TaskEntity(getName(), getDescription());
    }
}
