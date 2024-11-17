import React, { useEffect, useState } from 'react';
import axios from 'axios';
import config from './config.json';



const NFTListings = () => {
    const [nfts, setNfts] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const itemsPerPage = 1;

    useEffect(() => {
        const fetchCollections = async () => {
            try {
                // Retrieve collections from OpenSea API using collections stored in mongoDB
                const response = await axios.get('http://localhost:8080/nft_project/collections/urls');
                const collections = response.data;

                const responses = await Promise.all(collections.map(collection => axios.get(collection, {
                    params: {
                        owner: config.owner,
                        order_direction: 'desc',
                        offset: 0,
                        limit: 10
                    },
                    headers: {
                        'Accept': 'application/json',
                        'X-API-KEY': config.apiKey
                    }
                })));

                const data = responses.map(response => response.data);
                setNfts(data);

                // Save collections to MongoDB
                for (const collection of data) {
                    await axios.post('http://localhost:8080/nft_project/collections', {
                        id: collection.id,
                        name: collection.name,
                        collection: collection.collection,
                        category: collection.category,
                        url: collection.opensea_url,
                        description: collection.description,
                        imageUrl: collection.image_url
                    });
                }
            } catch (error) {
                console.error('Error checking collection existence:', error);
            }
        };

        fetchCollections().then(r => console.log('Collections fetched'));
    }, []);

    const handleNextPage = () => {
        if (currentPage < nfts.length - 1) {
            setCurrentPage(currentPage + 1);
        }
    };

    const handlePreviousPage = () => {
        if (currentPage > 0) {
            setCurrentPage(currentPage - 1);
        }
    };

    const handlePurchase = (nft) => {
        // Implement purchase logic here
        console.log(`Purchasing NFT: ${nft.name}`);
    };

    const handleSale = (nft) => {
        // Implement purchase logic here
        console.log(`Selling NFT: ${nft.name}`);
    };


    return (
        <div>
            <h1>Sulaiman's NFT Listings</h1>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>NFT</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                {nfts.slice(currentPage * itemsPerPage, (currentPage + 1) * itemsPerPage).map((collection, index) => (
                    <tr key={index}>
                        <td>{collection.name}</td>
                        <td>{collection.description}</td>
                        <td><img src={collection.image_url} alt={collection.name} width="200"/></td>
                        <td>
                            <div style={{display: 'flex', flexDirection: 'column'}}>
                                <button onClick={() => handlePurchase(collection)}>Purchase</button>
                                <button onClick={() => handleSale(collection)}>Sell</button>
                            </div>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <div>
                <button onClick={handlePreviousPage} disabled={currentPage === 0}>Previous</button>
                <button onClick={handleNextPage} disabled={currentPage >= nfts.length - 1}>Next</button>
            </div>
        </div>
    );

};


export default NFTListings;