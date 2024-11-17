import React from 'react';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import './App.css';
import NFTListings from './NFTListings';
import AdminPage from './AdminPage';

function App() {
  return (
      <Router>
        <div className="App">
          <header className="App-header">
            <nav>
              <Link to="/">Home</Link>
              <Link to="/admin">Admin</Link>
            </nav>
            <Routes>
              <Route path="/" element={<NFTListings />} />
              <Route path="/admin" element={<AdminPage />} />
            </Routes>
            <h1>Welcome to the UCF FinTech NFT Marketplace Project by Sulaiman Karmali!</h1>
          </header>
        </div>
      </Router>
  );
}

export default App;