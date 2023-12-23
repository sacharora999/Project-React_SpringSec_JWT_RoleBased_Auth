import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { compTodo, getAllTodos, incompTodo, delTodos } from './ToDoService';
import { isAdminUser } from './AuthService';

export default function ListToDoComponent() {

  const [todo, setTodo] = useState([]);
  const navigator = useNavigate();

  const isAdmin = isAdminUser();
  

  useEffect(()=>{
    getAllTodos().then((response)=>{setTodo(response.data)}).catch((error)=>{console.log(error)});
  },[])

  function updateToDo(id){
    navigator(`/upd-todo/${id}`)
  }

  function deleteToDo(id){
    delTodos(id).then((response) => {console.log("ToDo deleted")});
  }

  function addTodo(){
    navigator("/add-todo")
  }


  function completeToDo(id){
    compTodo(id).then((response) => {console.log("ToDo Completed")});
  }

  function incompleteToDo(id){
    incompTodo(id).then((response) => {console.log("ToDo Completed")});
  }


  return (
    <div className='container'>

      {
        isAdmin && 
        <button type="button" className="btn btn-primary" onClick={addTodo}>Add ToDo</button>
      }
     
      
      <table className='table table-striped table-bordered'>
       <thead>
        <tr>
            <th>ToDo Id</th>
            <th>ToDo Titile</th>
            <th>ToDo Description</th>
            <th>ToDo Completed</th>
            <th>Actions</th>
        </tr>
       </thead>
       <tbody>
        
            {todo.map((item) =>
                <tr key={item.id}>
                    <td>{item.id}</td>
                    <td>{item.title}</td>   
                    <td>{item.description}</td>  
                    <td>{item.completed ? "Yes" : "No"}</td>  
                    
                    <td>{isAdmin && <button type="submit" className="btn btn-warning" onClick={()=>updateToDo(item.id)}>Update</button>}
                    {isAdmin && <button type="submit" className="btn btn-danger" onClick={()=>deleteToDo(item.id)} style={{marginLeft: '10px'}}>Delete</button>}
                    <button type="submit" className="btn btn-success" onClick={()=>completeToDo(item.id)} style={{marginLeft: '10px'}}>Complete</button>
                    <button type="submit" className="btn btn-warning" onClick={()=>incompleteToDo(item.id)} style={{marginLeft: '10px'}}>InComplete</button></td>
                </tr>
            )}
        </tbody>
      </table>  
    </div>
  )
}
