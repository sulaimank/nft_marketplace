// npx hardhat run scripts/deploy.js --network your_network
async function main() {
    const [deployer] = await ethers.getSigners();
    console.log("Deploying contracts with the account:", deployer.address);

    const NFT_Token = await ethers.getContractFactory("NFT_Token");
    const nft = await NFT_Token.deploy();
    console.log("NFT contract deployed to:", nft.address);
}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    });
