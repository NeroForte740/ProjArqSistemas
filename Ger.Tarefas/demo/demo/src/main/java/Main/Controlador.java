package Main;

public class Controlador {

    private Gerenciador taskManager;

    public Controlador(Gerenciador taskManager) {
        this.taskManager = taskManager;
    }

    public void addTask(String description) {
        taskManager.addTask(description);
    }

    public void completeTask(int index) {
        taskManager.completeTask(index);
    }

    public void removeTask(int index) {
        taskManager.removeTask(index);
    }
}