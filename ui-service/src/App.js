import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import RegistrationPage from './view/registration/registration';
import LoginPage from './view/login/login';
import './App.css';
import Dashboard from "./view/dashboard/dashboard";
import AddRelative from "./view/dashboard/addRelative";
import ProtectedRoute from './routes/protectedRoute';

function App() {
  return (
  <Router>
    <div className="App">
      <Routes>
        <Route path="/" element={<LoginPage />}/>
        <Route path="/registration" element={<RegistrationPage />} />
        <Route element={<ProtectedRoute />}>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/add-relative" element={<AddRelative />} />
        </Route>
      </Routes>
    </div>
  </Router>

  );
}

export default App;
