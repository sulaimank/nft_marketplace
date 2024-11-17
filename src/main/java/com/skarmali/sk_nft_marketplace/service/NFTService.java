package com.skarmali.sk_nft_marketplace.service;

import com.skarmali.sk_nft_marketplace.contracts.NFT_Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
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
        // Check connection
        Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
        if (web3ClientVersion.hasError()) {
            throw new RuntimeException("Error connecting to Ethereum network: " + web3ClientVersion.getError().getMessage());
        }

        // Deploy contract
        NFT_Token contract = NFT_Token.deploy(web3j, credentials, gasProvider, "UCF NFT Marketplace", "SUCF").send();

        return contract.getContractAddress();
    }

    public BigInteger getBalance() throws Exception {
        EthGetBalance ethGetBalance = web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        BigInteger balance = ethGetBalance.getBalance();
        BigDecimal balanceInEther = Convert.fromWei(balance.toString(), Convert.Unit.ETHER);

        return balanceInEther.toBigInteger();
    }
}
