import java.util.ArrayList;
import java.util.List;
	
public class Gerenciador {
	    private List<Tarefa> tasks;
	    private List<Observer> observers;

	    public Gerenciador() {
	        tasks = new ArrayList<>();
	        observers = new ArrayList<>();
	    }

	    public void addTask(Tarefa task) {
	        tasks.add(task);
	        notifyObservers();
	    }

	    public void removeTask(Tarefa task) {
	        tasks.remove(task);
	        notifyObservers();
	    }

	    public void completeTask(Tarefa task) {
	        task.markAsCompleted();
	        notifyObservers();
	    }

	    public List<Tarefa> getTasks() {
	        return tasks;
	    }

	    public void addObserver(Observer observer) {
	        observers.add(observer);
	    }

	    public void removeObserver(Observer observer) {
	        observers.remove(observer);
	    }

	    private void notifyObservers() {
	        for (Observer observer : observers) {
	            observer.update();
	        }
	    }
	}
