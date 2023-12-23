import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { addTodos, updateTodos, getAllTodo } from "./ToDoService";

export default function ToDoComponent() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [completed, setCompleted] = useState("");
  
  const navigator = useNavigate();
  const { id } = useParams();


  const [error, setError] = useState({ title: "", description: "", completed: "" });

  function validateForm() {
    let valid = true;
    const errorsCopy = { ...error };
    if (title.trim()) {
      errorsCopy.title = "";
    } else {
      errorsCopy.title = "Title Required";
      valid = false;
    }

    if (description.trim()) {
      errorsCopy.description = "";
    } else {
      errorsCopy.description = "Description Required";
      valid = false;
    }

    if (completed) {
      errorsCopy.completed = "";
    } else {
      errorsCopy.completed = "Completed Required";
      valid = false;
    }

  

    setError(errorsCopy);
    return valid;
  }

  function addMeUp(todo){
    addTodos(todo)
          .then((response) => {
            console.log(response.data);
          });
  }

  function clickHandler(event) {
    event.preventDefault();
    if (validateForm()) {
      const todos = { title, description, completed };
      if (id) {
        updateTodos(id, todos)
          .then((response) => {
            console.log(response.data);
          });
      } else {
        addMeUp(todos);
      }
      navigator("/todos");
    }
  }


  useEffect(() => {
    if (id) {
      axios
      getAllTodo(id).then((response) => {
            setTitle(response.data.title),
            setDescription(response.data.description),
            setCompleted(response.data.completed);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, [id]);

  return (
    <div>
      {id ? <h3>Update ToDo</h3> : <h3>Add ToDo</h3>}
      <form>
        <div className="mb-3">
          <label htmlFor="title" className="form-label">
            Title
          </label>
          <input
            type="title"
            className={`form-control ${error.title ? "is-invalid" : ""}`}
            id="title"
            aria-describedby="title"
            value={title}
            onChange={(event)=> setTitle(event.target.value)}
          />
          {error.title && <div className="invalid-feedback">{error.title}</div>}
        </div>

        <div className="mb-3">
          <label htmlFor="description" className="form-label">
            Description
          </label>
          <input
            type="description"
            className={`form-control ${error.description ? "is-invalid" : ""}`}
            id="descrtiipon"
            aria-describedby="description"
            value={description}
            onChange={(event)=> setDescription(event.target.value)}
          />
          {error.description && <div className="invalid-feedback">{error.description}</div>}
        </div>

        <div className="mb-3">
          <label htmlFor="completed" className="form-label">
            Completed
          </label>
          <input
            type="completed"
            className={`form-control ${error.completed ? "is-invalid" : ""}`}
            id="completed"
            aria-describedby="completed"
            value={completed}
            onChange={(event)=> setCompleted(event.target.value)}
          />
          {error.completed && <div className="invalid-feedback">{error.completed}</div>}
        </div>

        

        <button
          type="submit"
          className="btn btn-primary"
          onClick={clickHandler}
        >
          Submit
        </button>
      </form>
    </div>
  );
}