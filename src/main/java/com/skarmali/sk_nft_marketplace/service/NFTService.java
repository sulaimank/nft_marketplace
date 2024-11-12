package com.skarmali.sk_nft_marketplace.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@Service
public class NFTService {
    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;

    public NFTService(@Value("${ethereum.node.url}") String nodeUrl,
                      @Value("${ethereum.wallet.privateKey}") String privateKey) {
        this.web3j = Web3j.build(new HttpService(nodeUrl));
        this.credentials = Credentials.create(privateKey);
        this.gasProvider = new StaticGasProvider(BigInteger.valueOf(20_000_000_000L), BigInteger.valueOf(6_721_975L));
    }

    public String deployContract() throws Exception {
//        SimpleNFT contract = SimpleNFT.deploy(
//                web3j,
//                credentials,
//                gasProvider,
//                BigInteger.valueOf(1000000) // Initial supply
//        ).send();
//        return contract.getContractAddress();
        return "CONTRACT_ADDRESS"; // Replace with actual deployed address
    }

    public BigInteger getBalance(String address) throws Exception {
//        SimpleNFT contract = SimpleNFT.load(
//                "CONTRACT_ADDRESS", // Replace with actual deployed address
//                web3j,
//                credentials,
//                gasProvider
//        );
//        return contract.balanceOf(address).send();
        return BigInteger.ZERO;
    }
}
