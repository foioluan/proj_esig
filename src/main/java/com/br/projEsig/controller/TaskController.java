package com.br.projEsig.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.br.projEsig.core.security.AuthUser;
import com.br.projEsig.domain.Task;
import com.br.projEsig.domain.User;
import com.br.projEsig.service.TaskService;
import com.br.projEsig.service.UserService;
import com.sun.xml.bind.v2.runtime.output.NamespaceContextImpl.Element;

@ManagedBean(name="taskController")
@ViewScoped
public class TaskController implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private TaskService service = new TaskService();
    private UserService userService = new UserService();
    private List<Task> taskList = new ArrayList<>();
    private List<User> managerList = new ArrayList<>();
    
    private String title = "";
    private String description = "";
    private String priority = "All";
	private String deadline = "";
    private String manager = "All";
    
    private String currentName = AuthUser.currentUserName;
    private String currentEmail = AuthUser.currentUserEmail;
    
    public TaskController() {
    	if (this.taskList.isEmpty()) {
            this.taskList = service.filterFind(title, description, manager, priority); 
        }
        this.managerList = userService.findAll();
        taskDeadlineVerify();
    }
    
    public void searchTasks() {
        this.taskList = service.filterFind(title, description, manager, priority);
    } 
    
    public void deleteTask(Long id) {
    	service.delete(id);
    	this.searchTasks();
    }
    
    public void concludeTask(Long id) {
    	Task task = service.findById(id);
    	task.setStatus("Concluded");
    	
    	service.update(task, id);
    	this.searchTasks();
    }
    
    public String saveTask(){	
    	Task task = new Task();
    	User user = userService.findByName(manager);
    	
    	task.setTitle(title);
    	task.setDescription(description);
    	task.setPriority(priority);
    	task.setDeadline(deadline);
    	task.setManager(user);
    	
    	service.save(task);
    	this.searchTasks();
    	
    	return "home?faces-redirect=true";
    }
    
    public String redirectToEditTask(Long taskId, String taskTitle, String taskDescription, String taskDeadline) {
    	return "editTask?faces-redirect=true&taskId=" + taskId + "&taskTitle=" + taskTitle + "&taskDescription=" + taskDescription + "&taskDeadline=" + taskDeadline;
    }
    
    private void taskDeadlineVerify() {
        int currentYear = LocalDateTime.now().getYear();
        int currentMonth = LocalDateTime.now().getMonthValue();
        int currentDay = LocalDateTime.now().getDayOfMonth();

        this.taskList.forEach(task -> {
            int year = Integer.parseInt(task.getDeadline().split("-")[0]);
            int month = Integer.parseInt(task.getDeadline().split("-")[1]);
            int day = Integer.parseInt(task.getDeadline().split("-")[2]);

            boolean isExpired = (year < currentYear) ||
                                (year == currentYear && month < currentMonth) ||
                                (year == currentYear && month == currentMonth && day < currentDay);

            if (isExpired) {
                task.setStatus("Expired");
                service.update(task, task.getId());
            }
        });

        this.searchTasks();
    }
    
    public List<User> getManagerList() {
    	return this.managerList;
    }
    
    public List<Task> getTaskList() {
    	return this.taskList;
    }

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getPriority() {
		return priority;
	}

	public String getDeadline() {
		return deadline;
	}

	public String getManager() {
		return manager;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getCurrentName() {
		return currentName;
	}

	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}

	public String getCurrentEmail() {
		return currentEmail;
	}

	public void setCurrentEmail(String currentEmail) {
		this.currentEmail = currentEmail;
	}
}
