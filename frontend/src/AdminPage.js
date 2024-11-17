import React, {useState} from 'react';
import axios from 'axios';
import './AdminPage.css';

const AdminPage = () => {
    const [status, setStatus] = useState('');
    const [balance, setBalance] = useState('');
    const [address, setAddress] = useState('');

    const deployContract = async () => {
        try {
            const response = await axios.post('http://localhost:8080/nft_project/deploy');
            setStatus(`Contract deployed at address: ${response.data.contractAddress}`);
        } catch (error) {
            setStatus(`Error deploying contract: ${error.message}`);
        }
    };

    const getBalance = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/nft_project/balance/${address}`);
            setBalance(`Balance: ${response.data}`);
        } catch (error) {
            setBalance(`Error getting balance: ${error.message}`);
        }
    };

    return (
        <div className="admin-page">
            <h2>Admin Page</h2>
            <button onClick={deployContract}>Deploy Contract</button>
            <p>{status}</p>
            <input
                type="text"
                placeholder="Enter address"
                value={address}
                onChange={(e) => setAddress(e.target.value)}
            />
            <button onClick={getBalance}>Get Balance</button>
            <p>{balance}</p>
        </div>
    );
};

export default AdminPage;