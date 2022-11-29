import React from 'react';
import './App.css';

interface UserProps{
    name: String,
    email: String

}

const User = ({name,email}: UserProps) => {
  return (
    <div>
            <div>
                <p>Name  : {name}</p>
                <p>Email : {email}</p>
            </div>
    </div>
  );
}

export default User;
