// URL da API do back-end
const apiUrl = 'http://localhost:8080/api/tasks';

// Função para carregar as tarefas
function loadTasks() {
    fetch(apiUrl)
        .then(response => response.json())
        .then(tasks => {
            const taskList = document.getElementById('taskList');
            taskList.innerHTML = '';  // Limpa a lista antes de repopular
            tasks.forEach((task, index) => {
                const taskElement = document.createElement('div');
                taskElement.classList.add('task');

                const taskDescription = document.createElement('span');
                taskDescription.textContent = task.description;

                // Adiciona a classe 'completed' ou 'incomplete' conforme o status
                if (task.isCompleted) {
                    taskDescription.classList.add('completed');
                } else {
                    taskDescription.classList.add('incomplete');
                }

                // Adiciona o texto de status ao lado da descrição
                const statusText = document.createElement('span');
                statusText.textContent = task.isCompleted ? ' (Completa)' : ' (Incompleta)';
                taskDescription.appendChild(statusText);

                // Botões para completar ou remover a tarefa
                const completeButton = document.createElement('button');
                completeButton.textContent = task.isCompleted ? 'Marcar como Incompleta' : 'Marcar como Completa';
                completeButton.onclick = () => markAsCompleted();

                const removeButton = document.createElement('button');
                removeButton.textContent = 'Remover';
                removeButton.onclick = () => removeTask(index);

                taskElement.appendChild(taskDescription);
                taskElement.appendChild(completeButton);
                taskElement.appendChild(removeButton);

                taskList.appendChild(taskElement);
            });
        })
        .catch(error => console.error('Erro ao carregar tarefas:', error));
}

// Função para adicionar uma nova tarefa
function addTask() {
    const taskInput = document.getElementById('taskInput');
    const description = taskInput.value;

    if (description.trim()) {
        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(description)
        })
        .then(() => {
            taskInput.value = '';  // Limpa o campo de entrada
            loadTasks();  // Recarrega a lista de tarefas
        })
        .catch(error => console.error('Erro ao adicionar tarefa:', error));
    }
}
// Função para alternar o status da tarefa
function toggleTaskStatus(index, isCompleted) {
    fetch(`${apiUrl}/${index}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ isCompleted: !isCompleted })  // Envia o novo status
    })
    .then(() => loadTasks())  // Recarrega as tarefas após a alteração do status
    .catch(error => console.error('Erro ao alternar status da tarefa:', error));
}

// Função para remover uma tarefa
function removeTask(index) {
    fetch(`${apiUrl}/${index}`, {
        method: 'DELETE'
    })
    .then(() => loadTasks())  // Recarrega a lista de tarefas
    .catch(error => console.error('Erro ao remover tarefa:', error));
}

// Carregar as tarefas ao iniciar
window.onload = loadTasks;
