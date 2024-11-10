package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/tasks")
public class Application {

    private List<Tarefa> taskManager = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // Rota para obter todas as tarefas
    @GetMapping
    public List<Tarefa> getTasks() {
        return taskManager;
    }

    // Rota para adicionar uma nova tarefa
    @PostMapping
    public void addTask(@RequestBody String description) {
        taskManager.add(new Tarefa(description));
    }

    // Rota para alterar o status de uma tarefa
    @PutMapping("/{index}")
    public void completeTask(@PathVariable int index) {
        if (index >= 0 && index < taskManager.size()) {
            taskManager.get(index).markAsCompleted();
        }
    }

    // Rota para remover uma tarefa
    @DeleteMapping("/{index}")
    public void removeTask(@PathVariable int index) {
        if (index >= 0 && index < taskManager.size()) {
            taskManager.remove(index);
        }
    }
}
