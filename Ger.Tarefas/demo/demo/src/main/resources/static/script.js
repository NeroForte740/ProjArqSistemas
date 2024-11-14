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
                taskElement.classList.add(task.isCompleted ? 'completed' : 'incomplete');

                const taskDescription = document.createElement('span');
                taskDescription.textContent = task.description;

                // Criando o indicador de status
                const statusText = document.createElement('span');
                statusText.classList.add('status');
                statusText.textContent = task.isCompleted ? ' (Completa)' : ' (Pendente)';
                
                // Botões para completar ou remover a tarefa
                const completeButton = document.createElement('button');
                completeButton.textContent = task.isCompleted ? 'Marcar como Incompleta' : 'Marcar como Completa';
                completeButton.onclick = () => toggleTaskStatus(index, task.isCompleted);

                const removeButton = document.createElement('button');
                removeButton.textContent = 'Remover';
                removeButton.classList.add('remove');
                removeButton.onclick = () => removeTask(index);

                taskDescription.appendChild(statusText);  // Adiciona o status à descrição
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

// Função para alternar o status da tarefa (marcar como completa ou incompleta)
function toggleTaskStatus(index, isCompleted) {
    fetch(`${apiUrl}/${index}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ isCompleted: !isCompleted })  // Inverte o status
    })
    .then(() => {
        // Encontrar o elemento da tarefa na lista e atualizar o status
        const taskElement = document.querySelectorAll('.task')[index];
        const statusText = taskElement.querySelector('.status');
        const completeButton = taskElement.querySelector('button');

        // Atualiza o status da tarefa no front-end
        if (statusText) {
            statusText.textContent = !isCompleted ? ' (Completa)' : ' (Pendente)';
            statusText.classList.toggle('completed', !isCompleted);
            statusText.classList.toggle('incomplete', isCompleted);
        }

        // Atualiza o texto do botão
        completeButton.textContent = !isCompleted ? 'Marcar como Incompleta' : 'Marcar como Completa';
        
        // Atualiza a classe da tarefa para refletir o status
        taskElement.classList.toggle('completed', !isCompleted);
        taskElement.classList.toggle('incomplete', isCompleted);
    })
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
