package com.br.projEsig.service;

import java.util.List;

import com.br.projEsig.core.security.AuthUser;
import com.br.projEsig.domain.Task;
import com.br.projEsig.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
	
	public List<Task> filterFind(String title, String description, String manager, String priority) {
		StringBuilder queryString = new StringBuilder("SELECT t FROM Task t WHERE 1=1");
		UserService userService = new UserService();
		User user = new User();
		
		
		if (!title.trim().isEmpty()) {
			queryString.append(" AND LOWER(t.title) LIKE LOWER(:title)");
	    }
	    if (!description.trim().isEmpty()) {
	    	queryString.append(" AND LOWER(t.description) LIKE LOWER(:description)");
	    }
	    if (!manager.equals("All") && AuthUser.currentUserIsAdmin) {
	    	user = userService.findByName(manager);
	    	queryString.append(" AND t.manager.id = :managerId");
	    }
	    if (!priority.equals("All")) {
	    	queryString.append(" AND LOWER(t.priority) = LOWER(:priority)");
	    }
	    if(!AuthUser.currentUserIsAdmin) {
	    	queryString.append(" AND t.manager.id = :managerId");
	    }
	    
	    TypedQuery<Task> query = em.createQuery(queryString.toString(), Task.class);
	    
	    
	    if (!title.trim().isEmpty()) {
	        query.setParameter("title", "%" + title + "%");
	    }
	    if (!description.trim().isEmpty()) {
	        query.setParameter("description", "%" + description + "%");
	    }
	    if (!manager.equals("All") && AuthUser.currentUserIsAdmin) {
	    	query.setParameter("managerId", user.getId());
	    }
	    if (!priority.equals("All")) {
	        query.setParameter("priority", priority);
	    }
	    if(!AuthUser.currentUserIsAdmin) {
	    	query.setParameter("managerId", AuthUser.currentUserId);
	    }
	    
	    List<Task> list = query.getResultList();
	    
	    return list;
	}
	
	public void save(Task task) {
		em.getTransaction().begin();
		
		em.persist(task);
		
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
