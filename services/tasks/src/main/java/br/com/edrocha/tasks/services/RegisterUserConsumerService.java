package br.com.edrocha.tasks.services;

import br.com.edrocha.tasks.dtos.users.CreateUserQueue;
import br.com.edrocha.tasks.entities.UserEntity;
import br.com.edrocha.tasks.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserConsumerService {

    @Autowired
    private UserRepository repository;

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var createUser = mapper.readValue(user, CreateUserQueue.class);
        repository.save(createUser.toEntity());
    }

}
