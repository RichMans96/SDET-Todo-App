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

  console.log(todoLists);

  const addTodo = (text, id) => {
    axios
      .post('http://localhost:8080/todo', {
        todoData: text,
        todoStatus: false,
        todoList: {
          listId: id,
        },
      })
      .then((response) => console.log(response))
      .catch((error) => console.log(error));
    window.location.reload(false);
  };

  const addList = (text) => {
    axios
      .post('http://localhost:8080/todolist', {
        listName: text,
      })
      .then((response) => console.log(response))
      .catch((error) => console.log(error));
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
          <ListCard key={index} index={index} data={list} addTodo={addTodo} />
        ))}
        <ListInput addList={addList} />
      </div>
    </div>
  );
}

export default App;
