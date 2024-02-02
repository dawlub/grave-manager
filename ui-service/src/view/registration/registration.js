import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './registration.css';
import axios from 'axios';
import logo from '../../img/logo.svg';

const RegistrationPage = () => {
    const [user, setUser] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        passwordConfirmation: '', // Use this name if it matches your API expectation
    });
    const [messages, setMessages] = useState([]);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (user.password !== user.passwordConfirmation) {
            setMessages(['Passwords do not match']);
            return;
        }

        try {
            const response = await axios.post('http://localhost:8080/v1/user/registration', user);
            if (response.status === 200 || response.status === 201) {
                setMessages(['User registered successfully']);
                navigate('/'); // Assuming you want to navigate to the homepage or login page
            }
        } catch (error) {
            if (error.response) {
                setMessages([error.response.data.message || 'Error during registration']);
            } else if (error.request) {
                setMessages(['No response from server']);
            } else {
                setMessages(['Error: ' + error.message]);
            }
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUser(prevUser => ({
            ...prevUser,
            [name]: value
        }));
    };

    return (
        <div className="container">
            <div className="logo">
                <img src={logo} alt="logo" />
            </div>
            <div className="registration-container">
                <form className="registration-form" onSubmit={handleSubmit}>
                    <div className="messages">
                        {messages.map((message, index) => <div key={index}>{message}</div>)}
                    </div>
                    <input
                        name="firstName"
                        type="text"
                        placeholder="First name"
                        value={user.firstName}
                        onChange={handleChange}
                    />
                    <input
                        name="lastName"
                        type="text"
                        placeholder="Last name"
                        value={user.lastName}
                        onChange={handleChange}
                    />
                    <input
                        name="email"
                        type="text"
                        placeholder="email@email.com"
                        value={user.email}
                        onChange={handleChange}
                    />
                    <input
                        name="password"
                        type="password"
                        placeholder="Password"
                        value={user.password}
                        onChange={handleChange}
                    />
                    <input
                        name="passwordConfirmation"
                        type="password"
                        placeholder="Confirm password"
                        value={user.passwordConfirmation}
                        onChange={handleChange}
                    />
                    <button type="submit" className="submit-relative-button">Create Account</button>
                </form>
            </div>
        </div>
    );
};

export default RegistrationPage;