import React, { useEffect, useState } from "react";
import axios from "axios";
import '../styles/Dashboard.css';
;

const Dashboard = () => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        axios.get('http://localhost:8080/oauth2', { withCredentials: true })
            .then(response => {
                setUser(response.data);
            })
            .catch(error => {
                console.error('Error occurred', error);
            });
    }, []);

    return (
        <div className="dashboard-container">
            <h2 className="dashboard-title">Dashboard</h2>
            {user ? (
                <div className="oauth2">
                    <p><strong>Name:</strong> {user.name}</p>
                    <p><strong>Email:</strong> {user.email}</p>
                    <p><strong>JWT_token:</strong> {user.jwt_token}</p>
                    {user.picture && (
                        <img
                            src={user.picture}
                            alt="User Profile"
                            className="profile-pic"
                            referrerPolicy="no-referrer"
                        />
                    )}
                </div>
            ) : (
                <p className="loading-text">Loading User Data...</p>
            )}
        </div>
    );
};

export default Dashboard;
