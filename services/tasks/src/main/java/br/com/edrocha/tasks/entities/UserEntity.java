package br.com.edrocha.tasks.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserEntity {
    @Id
    private Long id;
    private String name;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<TaskEntity> tasks;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserEntity(Long id, String name, String email) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
    }
}
