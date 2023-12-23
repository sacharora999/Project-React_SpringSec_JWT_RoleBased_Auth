import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginAPICall, savedLoggedInUser, storeToken } from "./AuthService";


export default function LoginComponent() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  
  const navigator = useNavigate();



  const [error, setError] = useState({username: "",password: "" });

  function validateForm() {
    let valid = true;
    const errorsCopy = { ...error };
    
    if (username.trim()) {
      errorsCopy.username = "";
    } else {
      errorsCopy.username = "Username Required";
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



  async function clickHandler(event) {
    event.preventDefault();
    if (validateForm()) {
      
      
        await loginAPICall(username, password)
          .then((response) => {
            const token = "Bearer " + response.data.accessToken
            const role = response.data.role
            storeToken(token)
            savedLoggedInUser(username, role)
            navigator("/todos");
        });

        // const token = "Basic " + window.btoa(username + ":" + password);
        // const token = "Bearer " + response.data.accessToken
        
        
      
    }
  }




  return (
    <div>
      <form>
        

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