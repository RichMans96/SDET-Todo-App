const container = document.querySelector('#list-container');

async function fetchAllTodoLists() {
  const response = await fetch('http://localhost:8080/todolist');
  const todoLists = await response.json();
  return todoLists;
}

fetchAllTodoLists().then((todoLists) => {
  const card = document.createElement('div');
  card.classList = 'card-container';

  todoLists.forEach((list) => {
    const content = `    
    <div class="card">
      <div class="card-header">
        <h1>${list.listName}</h1>
      </div>
    </div>
    `;
    card.innerHTML += content;
    container.appendChild(card);

    list.todos.forEach((todo) => {
      const content = `   
      <div class="card-body">
        <p>${todo.todoData}</p>
        <button type="button" class="btn btn-primary">Edit</button>
        <button type="button" class="btn btn-danger">Delete</button>
      </div>
      `;
      card.innerHTML += content;
    });
  });
});
