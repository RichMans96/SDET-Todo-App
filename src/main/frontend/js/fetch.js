async function fetchAllTodoLists() {
  const response = await fetch('http://localhost:8080/todolist');
  const todoLists = await response.json();
  console.log(todoLists);
  return todoLists;
}

fetchAllTodoLists();
