const form = document.querySelector('#createTodo');

form.addEventListener('submit', (event) => {
  createList(event);
});

function createList(event) {
  event.preventDefault();

  const todoData = document.querySelector('#todoData').value;
  const todoStatus = document.querySelector('#todoStatus').value;
  const listId = document.querySelector('#listId').value;

  fetch('http://localhost:8080/todo', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      todoData: todoData,
      todoStatus: todoStatus,
      todoList: {
        listId: listId,
      },
    }),
  });

  alert('Created successfully!');
}
