import React from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    faUserGroup,
    faCalendarCheck,
    faBell,
    faMessage,
    faGear,
    faRightFromBracket
} from '@fortawesome/free-solid-svg-icons';
import logo from '../../img/logo.svg'; // Adjust the import path as necessary

const NavigationPanel = () => {
    const navigate = useNavigate(); // Hook for programmatic navigation

    const handleLogout = () => {
        localStorage.removeItem('auth_token'); // Clear the authentication token
        navigate('/'); // Redirect to login page
    };

    return (
        <nav>
            <Link to="/dashboard">
                <img src={logo} alt="Logo" />
            </Link>
            <ul className="dashboard-menu">
                <li>
                    <FontAwesomeIcon icon={faUserGroup} />
                    <Link to="/dashboard" className="button">Relatives</Link>
                </li>
                <li>
                    <FontAwesomeIcon icon={faCalendarCheck} />
                    <Link className="button">Calendar</Link>
                </li>
                <li>
                    <FontAwesomeIcon icon={faBell} />
                    <Link className="button">Notifications</Link>
                </li>
                <li>
                    <FontAwesomeIcon icon={faMessage} />
                    <Link className="button">Messages</Link>
                </li>
                <li>
                    <FontAwesomeIcon icon={faGear} />
                    <Link className="button">Settings</Link>
                </li>
                <li>
                    <FontAwesomeIcon icon={faRightFromBracket} />
                    <Link to="/" onClick={handleLogout} className="button">Logout</Link>
                </li>
            </ul>
        </nav>
    );
};

export default NavigationPanel;
