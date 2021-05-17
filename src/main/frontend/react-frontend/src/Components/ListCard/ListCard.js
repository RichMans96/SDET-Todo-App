import React from 'react';
import Todo from './Todo/Todo';
import TodoInput from './TodoInput/TodoInput';

const ListCard = ({ data, addTodo, deleteList, deleteTodo }) => {
  const handleClick = () => {
    deleteList(data.listId);
  };

  return (
    <div id={data.listId} className='list-card'>
      <h1>{data.listName}</h1>
      {data.todos.map((todo, index) => (
        <Todo key={index} data={todo} index={index} listId={data.listId} deleteTodo={deleteTodo} />
      ))}
      <TodoInput listId={data.listId} addTodo={addTodo} />
      <button onClick={handleClick}>Delete List</button>
    </div>
  );
};

export default ListCard;
