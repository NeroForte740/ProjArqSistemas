import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Gerenciador taskManager = new Gerenciador();
        Controlador controller = new Controlador(taskManager);
        ObserverTarefa view = new ObserverTarefa(taskManager);

        // Adicionando tarefas
        System.out.println("Adicionar Tarefa:");
        controller.addTask(sc.nextLine());
        
        System.out.print("Adicionar nova Tarefa? (sim/não): ");
        String tarefa = sc.nextLine();
        
        while (tarefa.equalsIgnoreCase("sim")) {
            System.out.println("Adicionar Tarefa:");
            controller.addTask(sc.nextLine());
            System.out.println("Adicionar nova Tarefa? (sim/não): ");
            tarefa = sc.nextLine();
        }
        
        // Loop para completar e remover tarefas
        String action;
        do {
            view.displayTasks();
            System.out.print("Voce deseja (completar/remover) uma tarefa ou sair? ");
            action = sc.nextLine();

            if (action.equalsIgnoreCase("completar")) {
                System.out.println("Digite o lugar da tarefa a ser completada: ");
                int indexComplete = sc.nextInt() - 1;
                sc.nextLine();  // Limpa o buffer
                controller.completeTask(indexComplete);
            } else if (action.equalsIgnoreCase("remover")) {
                System.out.println("Digite o lugar da tarefa a ser removida: ");
                int indexRemove = sc.nextInt() - 1;
                sc.nextLine();  // Limpa o buffer
                controller.removeTask(indexRemove);
            }
        } while (!action.equalsIgnoreCase("sair"));

        System.out.println("Saindo...");
    }
}
