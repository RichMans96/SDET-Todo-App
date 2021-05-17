import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';
import ListCard from './Components/ListCard/ListCard';
import ListInput from './Components/ListInput/ListInput';

function App() {
  const [todoLists, setTodoLists] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  async function fetchData() {
    setIsLoading(true);
    const response = await axios('http://localhost:8080/todolist');
    const data = response.data;
    setTodoLists(data);
    setIsLoading(false);
  }

  useEffect(() => fetchData(), []);

  const addTodo = (text, id) => {
    const listId = id;
    const newTodo = {
      todoData: text,
      todoStatus: false,
      todoList: {
        listId: listId,
      },
    };
    axios
      .post('http://localhost:8080/todo', newTodo)
      .then((response) => {
        const newStateTodo = {
          id: response.data.id,
          todoData: response.data.todoData,
        };
        const newLists = [...todoLists];
        newLists.forEach((list) => {
          if (list.listId === listId) {
            list.todos.push(newStateTodo);
          }
        });
        setTodoLists(newLists);
      })
      .catch((error) => console.log(error));
  };

  const addList = (text) => {
    axios
      .post('http://localhost:8080/todolist', {
        listName: text,
      })
      .then((response) => {
        console.log(response);
        const newStateList = {
          listId: response.data.listId,
          listName: response.data.listName,
          todos: [],
        };
        const newLists = [...todoLists, newStateList];
        setTodoLists(newLists);
      })
      .catch((error) => console.log(error));
  };

  const deleteList = (id) => {
    axios
      .delete(`http://localhost:8080/todolist/${id}`)
      .then((response) => console.log(response))
      .catch((error) => console.log(error));
    const newLists = [...todoLists];
    const index = newLists.findIndex((list) => list.listId === id);
    if (index !== -1) newLists.splice(index, 1);
    setTodoLists(newLists);
  };

  const deleteTodo = (listId, id) => {
    axios
      .delete(`http://localhost:8080/todo/${id}`)
      .then((response) => console.log(response))
      .catch((error) => console.log(error));
    const newLists = [...todoLists];
    newLists.forEach((list) => {
      if (list.listId === listId) {
        const todoIndex = list.todos.findIndex((todo) => todo.id === id);
        if (todoIndex !== -1) list.todos.splice(todoIndex, 1);
      }
    });
    setTodoLists(newLists);
  };

  if (isLoading) {
    return (
      <div>
        <h1>Loading...</h1>
      </div>
    );
  }

  return (
    <div className='App'>
      <div className='main-container'>
        {todoLists.map((list, index) => (
          <ListCard
            key={index}
            index={index}
            data={list}
            addTodo={addTodo}
            deleteList={deleteList}
            deleteTodo={deleteTodo}
          />
        ))}
        <ListInput addList={addList} />
      </div>
    </div>
  );
}

export default App;
