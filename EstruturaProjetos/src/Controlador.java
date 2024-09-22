import java.util.List;

public class Controlador {
    private Gerenciador taskManager;

    public Controlador(Gerenciador taskManager) {
        this.taskManager = taskManager;
    }

    public void addTask(String taskDescription) {
        taskManager.addTask(new Tarefa(taskDescription));
    }

    public void completeTask(int index) {
        List<Tarefa> tasks = taskManager.getTasks();
        if (index >= 0 && index < tasks.size()) {
            Tarefa tarefa = tasks.get(index);
            tarefa.markAsCompleted();
            System.out.println("Tarefa marcada como completa: " + tarefa.getDescription());
        } else {
            System.out.println("Índice inválido. Nenhuma tarefa marcada como completa.");
        }
    }

    public void removeTask(int index) {
        List<Tarefa> tasks = taskManager.getTasks();
        if (index >= 0 && index < tasks.size()) {
            Tarefa tarefa = tasks.get(index);
            taskManager.removeTask(tarefa);
            System.out.println("Tarefa removida: " + tarefa.getDescription());
        } else {
            System.out.println("Índice inválido. Nenhuma tarefa removida.");
        }
    }
}
