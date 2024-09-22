import java.util.List;
public class ObserverTarefa implements Observer {
	
	    private Gerenciador taskManager;

	    public ObserverTarefa(Gerenciador taskManager) {
	        this.taskManager = taskManager;
	        this.taskManager.addObserver(this);
	    }

	    @Override
	    public void update() {
	        displayTasks();
	    }

	    public void displayTasks() {
	        List<Tarefa> tasks = taskManager.getTasks();
	        if (tasks.isEmpty()) {
	            System.out.println("Nenhuma tarefa disponivel.");
	            return;
	        }
	        
	        System.out.println("Tarefas:");
	        for (int i = 0; i < tasks.size(); i++) {
	            Tarefa tarefa = tasks.get(i);
	            String status = tarefa.isCompleted() ? "[Feito]" : "[Pendente]";
	            System.out.println((i + 1) + ": " + tarefa.getDescription() + " " + status);
	        }
	        
	    }


}
