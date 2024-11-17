import React, { useState } from 'react';
import axios from 'axios';
import './AdminPage.css';

const AdminPage = () => {
    const [status, setStatus] = useState('');

    const deployContract = async () => {
        try {
            const response = await axios.post('http://localhost:8080/deploy');
            setStatus(`Contract deployed at address: ${response.data.contractAddress}`);
        } catch (error) {
            setStatus(`Error deploying contract: ${error.message}`);
        }
    };

    return (
        <div className="admin-page">
            <h2>Admin Page</h2>
            <button onClick={deployContract}>Deploy Contract</button>
            <p>{status}</p>
        </div>
    );
};

export default AdminPage;