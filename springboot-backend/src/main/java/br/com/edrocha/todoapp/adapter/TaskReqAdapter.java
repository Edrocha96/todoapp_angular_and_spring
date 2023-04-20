package br.com.edrocha.todoapp.adapter;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.edrocha.todoapp.entity.Task;
import br.com.edrocha.todoapp.req.TaskReq;

@Component
public class TaskReqAdapter implements IDefaultAdapter<TaskReq, Task>{

	@Override
	public Task adapt(TaskReq req) {	
		return Task.builder().
			 idTask(generateId()).
			 nameTask(req.getNameTask()).
			 descriptionTask(req.getDescriptionTask()).
			 dateTask(parseDate(req.getDateTask(), req.getHourTask())).build();
	}

	private static String generateId() {
		
		LocalDateTime now = LocalDateTime.now();
		StringBuilder id = new StringBuilder();
		id.append(now.getYear());
		id.append(now.getMonthValue());
		id.append(now.getDayOfMonth());
		id.append(now.getSecond());
		
		return id.toString();
		
	}
	
	private LocalDateTime parseDate(String date, String hora) {
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(4, 5));
		int year = Integer.parseInt(date.substring(6, 10));
		int hour = Integer.parseInt(hora.substring(0, 2));
		int minute = Integer.parseInt(hora.substring(4, 5));
		LocalDateTime ld = LocalDateTime.of(year, month, day, hour, minute);
		return ld;
		
	}
	
}
