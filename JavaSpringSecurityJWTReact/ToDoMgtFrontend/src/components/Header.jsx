import React from "react";
import { isUserLoggedIn, logout } from "./AuthService";
import { NavLink } from "react-router-dom";
import { useNavigate } from 'react-router-dom'

export default function Header() {

  const isAuth = isUserLoggedIn()
  const navigator = useNavigate();

  function handleLogout(){
    logout()
    navigator('/login')
  }

  return (

    
    <div>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarTogglerDemo03"
          aria-controls="navbarTogglerDemo03"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <a className="navbar-brand" href="#">
          Welcome to ToDo Management Application
        </a>

       
        <div className="collapse navbar-collapse" id="navbarTogglerDemo03">
          <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
          { !isAuth && 
            <li className="nav-item active">
              <NavLink to="/register" className="nav-link">Register</NavLink>
            </li> 
          }
          </ul>

          <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
          { !isAuth && 
            <li className="nav-item active">
              <NavLink to="/login" className="nav-link">Login</NavLink>
            </li> 
          }
          </ul> 


          <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
          { isAuth && 
            <li className="nav-item active">
              <NavLink to="/login" className="nav-link" onClick={handleLogout}>Logout</NavLink>
            </li> 
          }
          </ul> 
        </div> 
      </nav>
    </div>
  );
}
