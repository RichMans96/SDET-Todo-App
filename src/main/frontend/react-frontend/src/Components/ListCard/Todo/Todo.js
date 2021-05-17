import React from 'react';

const Todo = ({ data, listId, deleteTodo }) => {
  const handleClick = () => {
    deleteTodo(listId, data.id);
  };
  return (
    <div className='todo'>
      <p>{data.todoData}</p>
      <button onClick={handleClick}> X </button>
    </div>
  );
};

export default Todo;
