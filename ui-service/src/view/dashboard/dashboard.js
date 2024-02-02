import React, { useState, useEffect } from 'react';
import RelativeItem from './relative'; // Import the component for individual relatives
import NavigationPanel from './navigationPanel'; // Adjust this path as necessary
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import { Link } from "react-router-dom";
import './dashboard.css'; // Ensure this path is correct for your CSS

const Dashboard = () => {
    const [relatives, setRelatives] = useState([]);
    const [searchQuery, setSearchQuery] = useState('');

    useEffect(() => {
        // Placeholder for fetching all relatives on component mount
        fetch('http://localhost:8080/v1/relative/all')
            .then(response => response.json())
            .then(data => setRelatives(data))
            .catch(error => console.error('Error fetching relatives:', error));
    }, []);

    const handleSearchChange = (e) => setSearchQuery(e.target.value);

    const handleSearchKeyPress = (e) => {
        if (e.key === 'Enter') {
            e.preventDefault(); // Prevent the default form submit behavior
            const endpoint = searchQuery.trim() ? `http://localhost:8080/v1/relative/search?query=${encodeURIComponent(searchQuery)}` : 'http://localhost:8080/v1/relative/all';
            fetch(endpoint)
                .then(response => response.json())
                .then(data => setRelatives(data))
                .catch(error => console.error('Error searching relatives:', error));
        }
    };

    return (
        <div className="dashboard-container">
            <NavigationPanel />
            <main>
                <header>
                    <div className="search-bar">
                        <div className="search-input-wrapper">
                            <FontAwesomeIcon icon={faMagnifyingGlass} className="search-icon" />
                            <input
                                className="search-input"
                                placeholder="Search relatives"
                                value={searchQuery}
                                onChange={handleSearchChange}
                                onKeyPress={handleSearchKeyPress}
                            />
                        </div>
                    </div>
                    <div className="add-relatives">
                        <Link to="/add-relative">
                            <FontAwesomeIcon icon={faPlus} /> add relative
                        </Link>
                    </div>
                </header>
                <section className="relatives">
                    {relatives.map(relative => (
                        <RelativeItem key={relative.id} relative={relative} />
                    ))}
                </section>
            </main>
        </div>
    );
};

export default Dashboard;