import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { getAuthToken } from '../axios/axiosConfig'; // Ensure this path is correct based on your project structure

const ProtectedRoute = () => {
    const isAuthenticated = getAuthToken(); // Implement this function to check for JWT token presence
    return isAuthenticated ? <Outlet /> : <Navigate to="/" />;
};

export default ProtectedRoute;