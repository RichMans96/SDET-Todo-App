import React, { useState } from 'react';

const TodoInput = ({ addTodo, listId }) => {
  const [input, setInput] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!input) return;
    addTodo(input, document.getElementById(listId).id);
    setInput('');
  };

  return (
    <form onSubmit={handleSubmit}>
      <input type='text' className='input' value={input} id={listId} onChange={(e) => setInput(e.target.value)} />
    </form>
  );
};

export default TodoInput;
