import React from 'react';
import Todo from './Todo/Todo';
import TodoInput from './TodoInput/TodoInput';

const ListCard = ({ data, addTodo }) => {
  return (
    <div id={`list-${data.listId}`} className='list-card'>
      <h1>{data.listName}</h1>
      {data.todos.map((todo, index) => (
        <Todo key={index} data={todo} index={index} />
      ))}
      <TodoInput listId={data.listId} addTodo={addTodo} />
    </div>
  );
};

export default ListCard;
