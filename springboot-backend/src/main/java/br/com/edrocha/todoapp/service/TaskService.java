package br.com.edrocha.todoapp.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.edrocha.todoapp.adapter.TaskReqAdapter;
import br.com.edrocha.todoapp.adapter.TaskRespAdapter;
import br.com.edrocha.todoapp.entity.Task;
import br.com.edrocha.todoapp.repository.TaskRepository;
import br.com.edrocha.todoapp.req.TaskReq;
import br.com.edrocha.todoapp.resp.TaskResp;

@Service
public class TaskService {
	
	private static Logger logger = LoggerFactory.getLogger(TaskService.class);
	
	private TaskRespAdapter taskAdapter;
	
	private TaskReqAdapter taskReqAdapter;
	
	private TaskRepository taskRepository;
	
	public TaskService(TaskRespAdapter taskAdapter, TaskReqAdapter taskReqAdapter, TaskRepository taskRepository) {
		this.taskAdapter = taskAdapter;
		this.taskReqAdapter = taskReqAdapter;
		this.taskRepository = taskRepository;
	}
	
	public List<TaskResp> findAll(){
		return taskAdapter.adapt(taskRepository.findAll());	
	}
	
	public TaskResp findById(String id){
	Task task = taskRepository.findById(id).orElse(null);
		return taskAdapter.adaptForTime(task);
	}
	
	public void save(TaskReq req) {
		try {
			Task task = taskReqAdapter.adapt(req);
			taskRepository.save(task);
		} catch (Exception e) {
			logger.error("Problemas ao salvar a tarefa.", e);
		}
		
	}
	
	public void update(TaskReq req) {
		try {
			Task task = taskRepository.findById(req.getIdTask()).orElse(null);
			if(task != null) {
			task.setNameTask(req.getNameTask());
			task.setDescriptionTask(req.getDescriptionTask());
			task.setDateTask(parseDate(req.getDateTask(), req.getHourTask()));
			taskRepository.save(task);
			}
		} catch (Exception e) {
			logger.error("Problemas ao atualizar a tarefa.", e);
		}
		
	}
	
	public void delete(String id) {
		try {
		String idRefactor = id.replace("=", "");
			taskRepository.deleteById(idRefactor);
		} catch (Exception e) {
			logger.error("Problemas ao excluir a tarefa.", e);
		}
	}

	private LocalDateTime parseDate(String date, String hora) {
		int day = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(4, 5));
		int year = Integer.parseInt(date.substring(6, 10));
		int hour = Integer.parseInt(hora.substring(0, 2));
		int minute = Integer.parseInt(hora.substring(3, 5));
		LocalDateTime ld = LocalDateTime.of(year, month, day, hour, minute);
		return ld;
		
	}

}
