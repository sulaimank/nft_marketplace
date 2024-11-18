package com.skarmali.sk_nft_marketplace;

import com.skarmali.sk_nft_marketplace.contracts.NFT_Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class NFT_TokenTest {
    @Value("${ethereum.node.testnet.url}")
    private String INFURA_URL;

    @Value("${ethereum.wallet.privateKey}")
    private String PRIVATE_KEY;

    private Web3j web3j;
    private Credentials credentials;
    private ContractGasProvider gasProvider;
    private NFT_Token contract;

    @BeforeEach
    public void setUp() throws Exception {
        web3j = Web3j.build(new HttpService(INFURA_URL));
        credentials = Credentials.create(PRIVATE_KEY);
        gasProvider = new DefaultGasProvider();

        contract = NFT_Token.deploy(web3j, credentials, gasProvider, "UCF NFT Marketplace", "SUCF").send();
    }

    @Test
    public void testContractListingPriceFunction() throws Exception {
        // Example test
        Integer result = contract.getListingPrice(BigInteger.ONE).send().intValue();
        assertNotNull(result);
    }
}
