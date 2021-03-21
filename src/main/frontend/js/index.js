document.addEventListener('DOMContentLoaded', () => {
  function fetchData() {
    fetch('http://localhost:8080/todolist')
      .then((resp) => resp.json())
      .then((data) => showAllTodoLists(data));
  }
  function showAllTodoLists(data) {
    for (const list of data) {
      const todoListContainer = document.querySelector('#list-container');
      const deleteBtn = document.createElement('button');
      const card = document.createElement('div');
      const cardHeader = document.createElement('div');
      const cardBody = document.createElement('div');
      const listTitle = document.createElement('h1');
      const todoWrapper = document.createElement('div');

      card.classList = 'card';
      cardHeader.classList = 'card-header';
      cardBody.classList = 'card-body';

      deleteBtn.innerHTML = 'Delete';
      deleteBtn.className = 'btn btn-danger';
      deleteBtn.addEventListener('click', () => deleteTodoList(list.listId));

      listTitle.innerHTML = list.listName;
      card.id = list.listId;

      cardHeader.appendChild(deleteBtn);
      cardHeader.appendChild(listTitle);
      card.appendChild(cardHeader);
      todoListContainer.appendChild(card);

      list.todos.forEach((todo) => {
        const todoData = document.createElement('p');
        const deleteBtn = document.createElement('button');
        const dropdownContainer = document.createElement('div');
        const form = document.createElement('form');
        const formContent = document.createElement('div');
        const formLabel = document.createElement('label');
        const formInput = document.createElement('input');
        const formBtn = document.createElement('button');
        const updateBtn = document.createElement('button');
        const singleTodo = document.createElement('div');

        deleteBtn.innerHTML = 'Delete';
        deleteBtn.className = 'btn btn-danger';
        deleteBtn.addEventListener('click', () => deleteTodo(todo.id));

        dropdownContainer.className = 'dropdown';

        updateBtn.innerHTML = 'Update';
        updateBtn.classList = 'btn btn-success dropdown-toggle';
        updateBtn.type = 'button';
        updateBtn.id = 'dropdownMenu2';
        updateBtn.setAttribute('data-toggle', 'dropdown');
        updateBtn.setAttribute('aria-haspopup', 'true');
        updateBtn.setAttribute('aria-expanded', 'false');

        form.className = 'dropdown-menu p-4';
        form.setAttribute('aria-labelledby', 'dropdownMenu2');
        formContent.className = 'form-group';
        formLabel.innerHTML = 'Update info here';
        formInput.type = 'text';
        formInput.className = 'dropdown-item';
        formInput.id = 'updatedInfo';
        formBtn.type = 'submit';
        formBtn.className = 'btn btn-primary';
        formBtn.innerHTML = 'Send';
        formBtn.addEventListener('click', (event) => updateTodo(event, todo, list));

        todoData.innerHTML = todo.todoData;
        singleTodo.id = todo.id;

        formContent.appendChild(formLabel);
        formContent.appendChild(formInput);
        form.appendChild(formContent);
        form.appendChild(formBtn);
        dropdownContainer.appendChild(updateBtn);
        dropdownContainer.appendChild(form);
        singleTodo.appendChild(todoData);
        singleTodo.appendChild(deleteBtn);
        singleTodo.appendChild(dropdownContainer);
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

      function deleteTodoList(listId) {
        const elToRemove = document.getElementById(listId);
        fetch(`http://localhost:8080/todolist/${listId}`, {
          method: 'DELETE',
        })
          .then(elToRemove.remove())
          .catch((err) => {
            console.log(err);
          });
      }

      function updateTodo(event, todo, list) {
        event.preventDefault();
        const newData = document.querySelector('#updatedInfo').value;
        fetch(`http://localhost:8080/todo/${todo.id}`, {
          method: 'PUT',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            todoData: newData,
            todoStatus: todo.todoStatus,
            todoList: {
              listId: list.listId,
            },
          }),
        })
          .then((response) => {
            console.log(response);
          })
          .catch((err) => {
            console.log(err);
          });
      }
    }
  }

  fetchData();
});
