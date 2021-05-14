import React, { useState } from 'react';

const ListInput = ({ addList }) => {
  const [input, setInput] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!input) return;
    addList(input);
    setInput('');
  };

  return (
    <div className='list-card'>
      <h1>Add A List</h1>
      <form onSubmit={handleSubmit}>
        <input type='text' className='input' value={input} id='input' onChange={(e) => setInput(e.target.value)} />
      </form>
    </div>
  );
};

export default ListInput;
