package com.skarmali.sk_nft_marketplace.service;

import com.skarmali.sk_nft_marketplace.contracts.NFT_Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.exceptions.ContractCallException;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class NFTService {
    private static final Logger logger = LoggerFactory.getLogger(NFTService.class);

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;
    private final String contractAddress;
    private final String mainnetUrl;
    private final String testnetUrl;

    public NFTService(@Value("${ethereum.node.testnet.url}") String testnetUrl,
                      @Value("${ethereum.node.mainnet.url}") String mainnetUrl,
                      @Value("${ethereum.wallet.privateKey}") String privateKey,
                      @Value("${ethereum.contract.address}") String contractAddress) {
        this.web3j = Web3j.build(new HttpService(testnetUrl));
        this.credentials = Credentials.create(privateKey);
        this.gasProvider = new DefaultGasProvider();
        this.contractAddress = contractAddress;
        this.mainnetUrl = mainnetUrl;
        this.testnetUrl = testnetUrl;
    }


    public String deployContract() throws Exception {
        // Check connection
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        if (web3ClientVersion.hasError()) {
            throw new RuntimeException("Error connecting to Ethereum network: " + web3ClientVersion.getError().getMessage());
        }

        // Deploy contract
        NFT_Token contract = NFT_Token.deploy(web3j, credentials, gasProvider, "UCF NFT Marketplace", "SUCF").send();
        logger.info("Contract deployed at address: " + contract.getContractAddress());

        return contract.getContractAddress();
    }

    public BigInteger getBalance() throws Exception {
        EthGetBalance ethGetBalance = web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        BigInteger balance = ethGetBalance.getBalance();
        BigDecimal balanceInEther = Convert.fromWei(balance.toString(), Convert.Unit.ETHER);

        return balanceInEther.toBigInteger();
    }

    public String sellNFT(String id) throws Exception {
        // Sell NFT
        NFT_Token contract = NFT_Token.load(contractAddress, web3j, credentials, gasProvider);

        return contract.sellNFT("nftContract", BigInteger.valueOf(1), BigInteger.valueOf(100)).send().getTransactionHash();
    }

    public String buyNFT(String id) throws Exception {
        // Buy NFT
        NFT_Token contract = NFT_Token.load(contractAddress, web3j, credentials, gasProvider);

        return contract.sellNFT("nftContract", BigInteger.valueOf(1), BigInteger.valueOf(100)).send().getTransactionHash();
    }

    public Integer getPrice(String id) throws Exception {
        // Sell NFT
        try {
            NFT_Token contract = NFT_Token.load(contractAddress, web3j, credentials, gasProvider);
            return contract.getListingPrice(BigInteger.valueOf(1)).send().intValue();
        } catch (ContractCallException e) {
            logger.error("Error calling contract method getListingPrice for NFT id {}: {}", id, e.getMessage());
            throw new RuntimeException("Error calling contract method getListingPrice: " + e.getMessage(), e);
        }
    }
}
