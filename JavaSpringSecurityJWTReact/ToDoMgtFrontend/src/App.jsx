import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import ListToDoComponent from "./components/ListToDoComponent";
import Header from "./components/Header";
import Footer from "./components/Footer";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import ToDoComponent from "./components/ToDoComponent";
import RegisterComponent from "./components/RegisterComponent";
import LoginComponent from "./components/LoginComponent";
import { isUserLoggedIn } from "./components/AuthService";

function App() {


  function AuthenticatedRoute({ children }) {
    const isAuth = isUserLoggedIn()

    if(isAuth) {
      return children
    }else{
      return <Navigate to ="/login" />
    }
  }
  
  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<ListToDoComponent />} />
          <Route path="/todos" element={<AuthenticatedRoute><ListToDoComponent /></AuthenticatedRoute>} />
          <Route path="/add-todo" element={<AuthenticatedRoute><ToDoComponent /></AuthenticatedRoute>} />
          <Route path="/upd-todo/:id" element={<AuthenticatedRoute><ToDoComponent /></AuthenticatedRoute>} />
          <Route path="/register" element={<RegisterComponent />} />
          <Route path="/login" element={<LoginComponent />} />
        </Routes>
        <Footer />
      </BrowserRouter>
    </>
  );
}

export default App;
