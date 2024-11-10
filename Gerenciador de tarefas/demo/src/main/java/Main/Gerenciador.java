package Main;

import java.util.ArrayList;
import java.util.List;

public class Gerenciador {
    private List<Tarefa> tasks;
    private List<Observer> observers;

    public Gerenciador() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public void addTask(String description) {
        tasks.add(new Tarefa(description));  // Criando uma nova tarefa
        notifyObservers();
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            notifyObservers();
        }
    }

    public void completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsCompleted();
            notifyObservers();
        }
    }

    public void updateTaskStatus(int index, boolean isCompleted) {
        if (index >= 0 && index < tasks.size()) {
            Tarefa task = tasks.get(index);
            task.setCompleted(isCompleted);  // Atualiza o status para o valor fornecido
            notifyObservers();
        }
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