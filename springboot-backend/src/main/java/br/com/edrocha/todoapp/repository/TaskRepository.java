package br.com.edrocha.todoapp.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.edrocha.todoapp.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String>{

}
