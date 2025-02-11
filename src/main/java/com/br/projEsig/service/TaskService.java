package com.br.projEsig.service;

import java.util.List;

import com.br.projEsig.domain.Task;
import com.br.projEsig.domain.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class TaskService {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("esigPU");
	
	EntityManager em = emf.createEntityManager();
	
	public List<Task> findAll() {
		List<Task> list = em.createQuery("FROM Task", Task.class).getResultList();
		
		return list;
	}
	
	public Task findById(Long id) {
		return em.find(Task.class, id);
	}
	
	public List<Task> filterFind(String title, String description, String manager, String priority, String status) {
		StringBuilder queryString = new StringBuilder("SELECT u FROM User u WHERE 1=1");
		UserService userService = new UserService();
		User user = userService.findByName(manager);
		
		if (title != null && !title.trim().isEmpty()) {
			queryString.append(" AND LOWER(t.title) LIKE LOWER(:title)");
	    }
	    if (description != null && !description.trim().isEmpty()) {
	    	queryString.append(" AND LOWER(t.description) LIKE LOWER(:description)");
	    }
	    if (manager != null && !manager.trim().isEmpty()) {
	    	queryString.append(" AND LOWER(t.manager) LIKE LOWER(:managerId)");
	    }
	    if (priority != null && !priority.trim().isEmpty()) {
	    	queryString.append(" AND LOWER(t.priority) = LOWER(:priority)");
	    }
	    if (status != null && !status.trim().isEmpty()) {
	    	queryString.append(" AND LOWER(t.status) = LOWER(:status)");
	    }
	    
	    TypedQuery<Task> query = em.createQuery(queryString.toString(), Task.class);
	    
	    if (title != null && !title.trim().isEmpty()) {
	        query.setParameter("title", "%" + title + "%");
	    }
	    if (description != null && !description.trim().isEmpty()) {
	        query.setParameter("description", "%" + description + "%");
	    }
	    if (manager != null && !manager.trim().isEmpty()) {
	        query.setParameter("manager", user.getId());
	    }
	    if (priority != null && !priority.trim().isEmpty()) {
	        query.setParameter("priority", priority);
	    }
	    if (status != null && !status.trim().isEmpty()) {
	        query.setParameter("status", status);
	    }
	    
	    List<Task> list = query.getResultList();
	    
	    return list;
	}
	
	public void save(Task task) {
		em.getTransaction().begin();
		
		em.merge(task);
		
		em.getTransaction().commit();
	}
	
	public void delete(Long id) {
		em.getTransaction().begin();
		
		Task task = this.findById(id);
		em.remove(task);
		
		em.getTransaction().commit();
	}
	
	public void update(Task task, Long id) {
		em.getTransaction().begin();
		
		Task updatedTask = this.findById(id);
		
		updatedTask.setTitle(task.getTitle());
		updatedTask.setDescription(task.getDescription());
		updatedTask.setPriority(task.getPriority());
		updatedTask.setStatus(task.getStatus());
		updatedTask.setDeadline(task.getDeadline());
		updatedTask.setManager(task.getManager());
		
		em.merge(updatedTask);
		
		em.getTransaction().commit();
	}
}
