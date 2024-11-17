// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.10;

import {Ownable} from "@openzeppelin/contracts/access/Ownable.sol";
import {ERC721} from "@openzeppelin/contracts/token/ERC721/ERC721.sol";
import {ERC721URIStorage} from "@openzeppelin/contracts/token/ERC721/extensions/ERC721URIStorage.sol";
import "@openzeppelin/contracts/token/ERC721/IERC721.sol";

contract NFT_Token is ERC721URIStorage,

Ownable {
    struct NFTListing {
        address seller;
        uint256 tokenId;
        address nftContract;
        uint256 price;
        bool isListed;
    }

    mapping(uint256 => NFTListing) private _listings;
    uint256 private _listingId = 0;
    uint256 private _tokenIds = 0;


    event NFTListed(uint256 listingId, address nftContract, uint256 tokenId, address seller, uint256 price);
    event NFTSold(uint256 tokenId, address buyer, address seller);

    constructor(string memory name, string memory symbol) ERC721(name, symbol) Ownable(msg.sender) {
    }


    function sellNFT(address nftContract, uint256 tokenId, uint256 price) public {
        require(price > 0, "Price must be greater than zero");
        IERC721 nft = IERC721(nftContract);
        require(nft.ownerOf(tokenId) == msg.sender, "You must own the NFT to sell it");
        require(nft.isApprovedForAll(msg.sender, address(this)), "NFT must be approved for marketplace");

        _listingId++;
        _listings[_listingId] = NFTListing(msg.sender, tokenId, nftContract, price, true);

        emit NFTListed(_listingId, nftContract, tokenId, msg.sender, price);
    }

    function listNFT(address nftContract, uint256 tokenId, uint256 price) public {

        require(ownerOf(tokenId) == msg.sender, "You must own the NFT to list it");
        require(price > 0, "Price must be greater than zero");

        _listings[tokenId] = NFTListing(msg.sender, tokenId, nftContract, price, true);

        emit NFTListed(_listingId, nftContract, tokenId, msg.sender, price);
    }


    function buyNFT(uint256 tokenId) public payable {
        NFTListing memory listing = _listings[tokenId];
        require(listing.price > 0, "NFT not listed for sale");
        require(msg.value >= listing.price, "Insufficient payment");

        address seller = listing.seller;

        delete _listings[tokenId];

        _transfer(seller, msg.sender, tokenId);
        payable(seller).transfer(listing.price);

        emit NFTSold(tokenId, msg.sender, seller);

        if (msg.value > listing.price) {
            payable(msg.sender).transfer(msg.value - listing.price);
        }
    }

    function cancelListing(uint256 tokenId) public {
        require(_listings[tokenId].seller == msg.sender, "You must be the seller to cancel the listing");
        delete _listings[tokenId];
    }

    function getListingPrice(uint256 tokenId) public view returns (uint256) {
        return _listings[tokenId].price;
    }
}