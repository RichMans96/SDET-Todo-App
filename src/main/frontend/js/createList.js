const form = document.querySelector('#createList');

form.addEventListener('submit', (event) => {
  createList(event);
});

function createList(event) {
  event.preventDefault();

  const listName = document.querySelector('#listname').value;

  fetch('http://localhost:8080/todolist', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      listName: listName,
    }),
  });
}
