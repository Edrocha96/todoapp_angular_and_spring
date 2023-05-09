package br.com.edrocha.tasks.dtos.users;

import br.com.edrocha.tasks.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserQueue {
    private Long id;
    private String name;
    private String email;

    public UserEntity toEntity() {
        return new UserEntity(this.getId(), this.getName(), this.getEmail());
    }
}
