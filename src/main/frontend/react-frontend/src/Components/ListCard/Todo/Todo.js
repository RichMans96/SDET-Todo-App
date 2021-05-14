import React from 'react';

const Todo = ({ data }) => {
  return (
    <div className='todo'>
      <p>{data.todoData}</p>
    </div>
  );
};

export default Todo;
