package com.br.projEsig.controller;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.br.projEsig.domain.Task;
import com.br.projEsig.domain.User;
import com.br.projEsig.service.TaskService;
import com.br.projEsig.service.UserService;

@ManagedBean(name="editTaskController")
@ViewScoped
public class EditTaskController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private TaskService service = new TaskService();
	private UserService userService = new UserService();
	
	private Long taskId;
	
	private String title = "";
    private String description = "";
	private String deadline = "";
	private String manager = "";
	private String priority = "";
    
    public EditTaskController() {
    	Map<String, String> params = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();
		String id = params.get("taskId");
		title = params.get("taskTitle");
		description = params.get("taskDescription");
		deadline = params.get("taskDeadline");
		taskId = Long.parseLong(id);
    }
	
	public String editTask(){	
    	Task task = service.findById(this.taskId);
    	User user = userService.findByName(manager);
    	
    	task.setTitle(title);
    	task.setDescription(description);
    	task.setPriority(priority);
    	task.setDeadline(deadline);
    	task.setManager(user);
    	
    	service.update(task, this.taskId);

    	return "home?faces-redirect=true";
    }
	
    public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
}
