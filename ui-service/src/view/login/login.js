import { setAuthHeader, request, getAuthToken } from '../../axios/axiosConfig';
import React, { useState, useEffect } from 'react';
import './login.css';
import logo from '../../img/logo.svg';
import { useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode';

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [messages, setMessages] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const token = getAuthToken();
        if (token) {
            const decodedToken = jwtDecode(token);
            const currentDate = new Date();

            // Check if token has expired
            if (decodedToken.exp * 1000 < currentDate.getTime()) {
                localStorage.removeItem('auth_token'); // If expired, remove token from localStorage
            } else {
                navigate('/dashboard'); // If valid, redirect to main page
            }
        }
    }, [navigate]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await request('post','/v1/auth/signup', { email, password });
            if (response.data.jwtToken) {
                setAuthHeader(response.data.jwtToken);
                console.log("Login successful, token:", response.data.jwtToken);
                navigate('/dashboard');
            } else {
                setMessages(["Login failed: No token received"]); // Ensure this is an array
            }
        } catch (error) {
            console.error("Login error:", error);
            setMessages([error.response?.data.message || 'Login failed']); // Ensure this is an array
        }
    };

    return (
        <div className="container">
            <div className="logo">
                <img src={logo} alt="logo" />
            </div>
            <div className="login-container">
                <form className="login-form" onSubmit={handleSubmit}>
                    <div className="messages">
                        {messages.map((message, index) => (
                            <div key={index}>{message}</div>
                        ))}
                    </div>
                    <input
                        name="email"
                        type="text"
                        placeholder="example@domain.com"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                    <input
                        name="password"
                        type="password"
                        placeholder="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <button type="submit">Login</button>
                    <a className="registration-link" href="/registration">Don't have an account? Sign up</a>
                </form>
            </div>
        </div>
    );
};

export default LoginPage;
