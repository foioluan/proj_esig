package com.br.projEsig.service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import com.br.projEsig.domain.Task;
import com.br.projEsig.domain.User;

public class Teste {

	public static void main(String[] args) {
		/*UserService userService = new UserService();
		TaskService taskService = new TaskService();
		
		User user = new User();
		user.setEmail("joao.vitor@gmail.com");
		user.setPassword("1998");
		user.setName("João");
		
		userService.save(user);
		
		userService.delete(user.getId());
		
		user = new User();
		
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
		task.setDeadline("2025-02-25");
		
		taskService.save(task);
		
		taskService.delete(task.getId());
		
		task = new Task();
		
		task.setTitle("Lavar a louça");
		task.setDescription("A pessoa será responsável por lavar e enxugar toda a louça, também como manter a pia limpa.");
		task.setPriority("Média");
		task.setManager(manager);
		task.setDeadline("2025-02-25");
		
		taskService.save(task);*/
		
		String dateString = "Tue Feb 18 21:00:00 BRT 2025";

        // Definir o formato para parse da string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");
        
        try {
            // Fazer o parse da string para ZonedDateTime
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString, formatter);
            
            // Converter para LocalDate (somente data)
            LocalDate localDate = zonedDateTime.toLocalDate();
            
            // Formatar a data no formato desejado "yyyy-MM-dd"
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = localDate.format(outputFormatter);
            
            // Exibir a data formatada
            System.out.println("\n\n\n\n" + formattedDate);  // Exemplo: 2025-02-18
        } catch (DateTimeParseException e) {
            System.out.println("Erro ao parsear a data: " + e.getMessage());
        }
	}
}
