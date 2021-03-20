document.addEventListener('DOMContentLoaded', () => {
  function fetchData() {
    fetch('http://localhost:8080/todolist')
      .then((resp) => resp.json())
      .then((data) => showAllTodoLists(data));
  }
  function showAllTodoLists(data) {
    for (const list of data) {
      const todoListContainer = document.querySelector('#list-container');
      const card = document.createElement('div');
      const cardHeader = document.createElement('div');
      const cardBody = document.createElement('div');
      const listTitle = document.createElement('h1');
      const todoWrapper = document.createElement('div');

      card.classList = 'card';
      cardHeader.classList = 'card-header';
      cardBody.classList = 'card-body';

      listTitle.innerHTML = list.listName;

      cardHeader.appendChild(listTitle);
      card.appendChild(cardHeader);
      todoListContainer.appendChild(card);

      list.todos.forEach((todo) => {
        const todoData = document.createElement('p');
        const deleteBtn = document.createElement('button');
        const singleTodo = document.createElement('div');

        deleteBtn.innerHTML = 'Delete';
        deleteBtn.className = 'btn btn-danger';
        deleteBtn.addEventListener('click', () => deleteTodo(todo.id));

        todoData.innerHTML = todo.todoData;
        singleTodo.id = todo.id;

        singleTodo.appendChild(todoData);
        singleTodo.appendChild(deleteBtn);
        todoWrapper.appendChild(singleTodo);
        cardBody.appendChild(todoWrapper);
        card.appendChild(cardBody);
      });

      function deleteTodo(id) {
        const elToRemove = document.getElementById(id);
        fetch(`http://localhost:8080/todo/${id}`, {
          method: 'DELETE',
        })
          .then(elToRemove.remove())
          .catch((err) => {
            console.log(err);
          });
      }
    }
  }

  fetchData();
});
