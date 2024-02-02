import React, { useState } from 'react';
import NavigationPanel from './navigationPanel'; // Adjust the import path as needed
import './add-relative.css'; // Ensure the CSS path is correct

const AddRelative = () => {
    const [formData, setFormData] = useState({
        firstName: '', // Now using firstName and lastName directly
        lastName: '',
        dateOfBirth: '',
        dateOfDeath: '',
        location: '',
        file: null
    });
    const [messages, setMessages] = useState([]);

    const handleChange = (e) => {
        const { name, value, files } = e.target;
        if (name === 'file') {
            setFormData(prevState => ({ ...prevState, file: files[0] }));
        } else if (name === 'fullName') {
            const [firstName, ...lastName] = value.split(' '); // Splits fullName into firstName and the rest as lastName
            setFormData(prevState => ({
                ...prevState,
                firstName: firstName,
                lastName: lastName.join(' ') // Joins the rest as lastName
            }));
        } else {
            setFormData(prevState => ({ ...prevState, [name]: value }));
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const data = new FormData();
        Object.entries(formData).forEach(([key, value]) => {
            if (key === 'file') {
                data.append('image', value); // Ensure the key matches your backend expectation
            } else if (value !== null) { // Avoid appending null values
                data.append(key, value);
            }
        });

        try {
            const response = await fetch('http://localhost:8080/v1/relative/create', {
                method: 'POST',
                body: data,
                // Note: When using FormData, the 'Content-Type' header is automatically set to 'multipart/form-data', and the boundary is added.
            });
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const result = await response.json();
            setMessages([result.message || 'Relative added successfully']);
        } catch (error) {
            console.error('Error submitting form:', error);
            setMessages(['Error submitting form. Please try again.']);
        }
    };

    return (
        <div className="dashboard-container">
            <NavigationPanel />
            <main className="add-relatives-main">
                <section className="add-relatives-section">
                    <h1>Add Relative to Database</h1>
                    <form onSubmit={handleSubmit} encType="multipart/form-data">
                        <div className="messages">
                            {messages.map((message, index) => <div key={index}>{message}</div>)}
                        </div>
                        <input
                            name="fullName"
                            type="text"
                            placeholder="Full name"
                            onChange={handleChange}
                            required
                        />
                        <input
                            name="dateOfBirth"
                            type="date"
                            placeholder="Date of birth"
                            onChange={handleChange}
                            required
                        />
                        <input
                            name="dateOfDeath"
                            type="date"
                            placeholder="Date of death (optional)"
                            onChange={handleChange}
                        />
                        <input
                            name="location"
                            type="text"
                            placeholder="Location"
                            onChange={handleChange}
                            required
                        />
                        <input
                            className="file-button"
                            type="file"
                            name="file"
                            onChange={handleChange}
                            required
                        />
                        <button className="submit-relative-button" type="submit">Add</button>
                    </form>
                </section>
            </main>
        </div>
    );
};

export default AddRelative;