package br.com.edrocha.todoapp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@Table(name = "tb_task")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Adaptable{

	@Id
	@Column(name = "ID_TASK")
	private String idTask;
	
	@Column(name = "NAME_TASK")
	private String nameTask;
	
	@Column(name = "DESCRIPTION_TASK")
	private String descriptionTask;
	
	@Column(name = "DATE_TASK")
	private LocalDateTime dateTask;
}
