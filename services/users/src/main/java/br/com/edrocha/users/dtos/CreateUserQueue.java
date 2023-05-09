package br.com.edrocha.users.dtos;

import br.com.edrocha.users.entities.UserEntity;
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

    public static CreateUserQueue byEntity(UserEntity user) {
        return new CreateUserQueue(user.getId(), user.getName(), user.getEmail());
    }
}
