import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";


export default function RegisterComponent() {
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  
  const navigator = useNavigate();



  const [error, setError] = useState({ name: "", username: "", email: "", password: "" });

  function validateForm() {
    let valid = true;
    const errorsCopy = { ...error };
    if (name.trim()) {
      errorsCopy.name = "";
    } else {
      errorsCopy.name = "Name Required";
      valid = false;
    }

    if (username.trim()) {
      errorsCopy.username = "";
    } else {
      errorsCopy.username = "Username Required";
      valid = false;
    }

    if (email.trim()) {
      errorsCopy.email = "";
    } else {
      errorsCopy.email = "Email Required";
      valid = false;
    }


    if (password.trim()) {
        errorsCopy.password = "";
      } else {
        errorsCopy.password = "Password Required";
        valid = false;
    }

  

    setError(errorsCopy);
    return valid;
  }



  function clickHandler(event) {
    event.preventDefault();
    if (validateForm()) {
      const regdata = { name, username, email, password };
      
        registerAPICall(regdata)
          .then((response) => {
            alert("User registration success message: " + response);
        });
       
      navigator("/register");
    }
  }




  return (
    <div>
      <form>
        <div className="mb-3">
          <label htmlFor="name" className="form-label">
            Name
          </label>
          <input
            type="name"
            className={`form-control ${error.name ? "is-invalid" : ""}`}
            id="name"
            aria-describedby="name"
            value={name}
            onChange={(event)=> setName(event.target.value)}
          />
          {error.name && <div className="invalid-feedback">{error.name}</div>}
        </div>

        <div className="mb-3">
          <label htmlFor="username" className="form-label">
            Username
          </label>
          <input
            type="username"
            className={`form-control ${error.username ? "is-invalid" : ""}`}
            id="username"
            aria-describedby="username"
            value={username}
            onChange={(event)=> setUsername(event.target.value)}
          />
          {error.username && <div className="invalid-feedback">{error.username}</div>}
        </div>

        <div className="mb-3">
          <label htmlFor="email" className="form-label">
            Email
          </label>
          <input
            type="email"
            className={`form-control ${error.email ? "is-invalid" : ""}`}
            id="email"
            aria-describedby="email"
            value={email}
            onChange={(event)=> setEmail(event.target.value)}
          />
          {error.email && <div className="invalid-feedback">{error.email}</div>}
        </div>


        <div className="mb-3">
          <label htmlFor="password" className="form-label">
            Password
          </label>
          <input
            type="password"
            className={`form-control ${error.password ? "is-invalid" : ""}`}
            id="password"
            aria-describedby="password"
            value={password}
            onChange={(event)=> setPassword(event.target.value)}
          />
          {error.password && <div className="invalid-feedback">{error.password}</div>}
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