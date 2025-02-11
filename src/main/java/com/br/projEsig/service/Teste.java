package com.br.projEsig.service;

import java.time.LocalDate;
import java.util.List;

import com.br.projEsig.domain.Task;
import com.br.projEsig.domain.User;

public class Teste {

	public static void main(String[] args) {
		UserService userService = new UserService();
		TaskService taskService = new TaskService();
		
		User user = new User();
		user.setEmail("joao.vitor@gmail.com");
		user.setPassword("1998");
		user.setName("João");
		
		userService.save(user);
		
		userService.delete(user.getId());
		
		user.setEmail("pedro@gmail.com");
		user.setPassword("1998");
		user.setName("Pedro");
		
		userService.save(user);
		
		List<User> list = userService.findAll();
		
		list.forEach(element -> System.out.println(element.getName()));
		
		User updated = new User();
		updated.setEmail("lucas@gmail.com");
		updated.setName("Lucas");
		
		userService.update(updated, user.getId());
		
		User manager = new User();
		manager.setEmail("andre.vitor@gmail.com");
		manager.setPassword("1998");
		manager.setName("André");
		
		userService.save(manager);
		
		Task task = new Task();
		task.setTitle("Code Debug");
		task.setDescription("Realizar o debug do código tal");
		task.setPriority("Alta");
		task.setManager(manager);
		task.setDeadline(LocalDate.of(2025, 2, 14));
		
		taskService.save(task);
	}
}
