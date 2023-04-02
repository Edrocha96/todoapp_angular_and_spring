package br.com.edrocha.todoapp.resp;

import br.com.edrocha.todoapp.entity.Adaptable;
import br.com.edrocha.todoapp.entity.IRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TaskResp implements IRequest, Adaptable{
	
	public String idTask;
	
	public String nameTask;

	public String descriptionTask;

	public String dateTask;
	
	public String hourTask;
	
}
