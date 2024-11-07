import logo from './logo.svg';
import './App.css';
import NFTListings from './NFTListings';


function App() {
  return (
    <div className="App">
      <header className="App-header">
        <NFTListings />
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Welcome to the NFT Marketplace Project by Sulaiman!
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          UCF FinTech NFT Marketplace Project
        </a>
      </header>
    </div>
  );
}

export default App;
