package projEsig;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.br.projEsig.domain.Task;
import com.br.projEsig.domain.User;
import com.br.projEsig.service.TaskService;
import com.br.projEsig.service.UserService;

public class PersistenceTests {

    private UserService userService;
    private TaskService taskService;

    @BeforeEach
    public void setup() {
        userService = new UserService();
        taskService = new TaskService();
    }

    @Test
    public void usertests() {
        User user = new User();
        user.setEmail("joao.vitor@gmail.com");
        user.setPassword("1998");
        user.setName("João");
        userService.save(user);

        Long joaoId = user.getId();
        userService.delete(joaoId);

        User pedro = new User();
        pedro.setEmail("pedro@gmail.com");
        pedro.setPassword("1998");
        pedro.setName("Pedro");
        userService.save(pedro);

        List<User> users = userService.findAll();
        boolean foundPedro = users.stream()
                                  .anyMatch(u -> "Pedro".equals(u.getName()));
        assertTrue(foundPedro, "O usuário 'Pedro' deve estar presente na lista.");

        User updated = new User();
        updated.setEmail("lucas@gmail.com");
        updated.setName("Lucas");
        userService.update(updated, pedro.getId());

        List<User> updatedUsers = userService.findAll();
        boolean foundLucas = updatedUsers.stream()
                                         .anyMatch(u -> "Lucas".equals(u.getName())
                                                  && "lucas@gmail.com".equals(u.getEmail()));
        assertTrue(foundLucas, "O usuário deve ter sido atualizado para 'Lucas'.");
    }

    @Test
    public void taskTests() {
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

        Long taskId = task.getId();
        assertNotNull(taskId, "A tarefa 'Code Debug' deve ter um ID atribuído após o salvamento.");

        taskService.delete(taskId);

        Task task2 = new Task();
        task2.setTitle("Lavar a louça");
        task2.setDescription("A pessoa será responsável por lavar e enxugar toda a louça, também como manter a pia limpa.");
        task2.setPriority("Média");
        task2.setManager(manager);
        task2.setDeadline("2025-02-25");
        taskService.save(task2);

        Long task2Id = task2.getId();
        assertNotNull(task2Id, "A tarefa 'Lavar a louça' deve ter um ID atribuído após o salvamento.");
    }
    
    @Test
    public void taskWithoutUser() {
        Task task = new Task();
        task.setTitle("Tarefa inválida");
        task.setDescription("Esta tarefa não tem um usuário responsável.");
        task.setPriority("Baixa");
        task.setDeadline("2025-02-25");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.save(task);
        });

        assertEquals("A tarefa deve ter um gerente associado.", exception.getMessage(), "A criação de tarefa sem gerente deve falhar.");
    }
}
