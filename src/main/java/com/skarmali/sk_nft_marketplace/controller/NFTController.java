package com.skarmali.sk_nft_marketplace.controller;

import com.skarmali.sk_nft_marketplace.contracts.NFT_Token;
import com.skarmali.sk_nft_marketplace.model.Collection;
import com.skarmali.sk_nft_marketplace.model.CollectionUrl;
import com.skarmali.sk_nft_marketplace.repository.CollectionRepository;
import com.skarmali.sk_nft_marketplace.repository.CollectionUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nft_project")
@CrossOrigin(origins = "http://localhost:3000")
public class NFTController {
    @Autowired
    private final CollectionRepository collectionRepository;

    @Autowired
    private final CollectionUrlRepository collectionUrlRepository;

    public NFTController(CollectionRepository collectionRepository, CollectionUrlRepository collectionUrlRepository) {
        this.collectionRepository = collectionRepository;
        this.collectionUrlRepository = collectionUrlRepository;
    }

    @GetMapping("/collections")
    public List<Collection> getCollections() {
        return collectionRepository.findAll();
    }

    @PostMapping("/collections")
    public ResponseEntity createCollection(@RequestBody Collection collection) throws URISyntaxException {
        Collection savedCollection = collectionRepository.save(collection);
        return ResponseEntity.created(new URI("/collections/" + savedCollection.getId())).body(savedCollection);
    }


    @GetMapping("/collections/{id}")
    public ResponseEntity<Collection> getCollectionById(@PathVariable String id) {
        Optional<Collection> collection = collectionRepository.findById(id);
        return collection.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/collections/name/{name}")
    public ResponseEntity<Collection> getCollectionByName(@PathVariable String name) {
        Optional<Collection> collection = collectionRepository.findByName(name);
        return collection.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/collections/urls")
    public List<String> getCollectionUrls() {
        // Return the list of collection URLs from MongoDB
        return collectionUrlRepository.findAll().stream()
                                   .map(CollectionUrl::getUrl)
                                   .collect(Collectors.toList());
    }

    @PostMapping("/deploy")
    public String deployNFTFromBytecode() throws Exception {
        // Load bytecode from resources
        ClassPathResource resource = new ClassPathResource("contracts/NFT_Token.bin");
        byte[] bytecode;
        try (InputStream inputStream = resource.getInputStream()) {
            bytecode = inputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load bytecode from resources", e);
        }

        // Connect to Ethereum test network (replace with your provider)
        Web3j web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/UCF_Project"));


        // Load credentials (replace with your private key)
        Credentials credentials = Credentials.create("bb2767e2d326ea2ebb1e1446c534e4263e1348f48ec431fc5d4445b5124f48ed");

        // Deploy contract
        ContractGasProvider gasProvider = new DefaultGasProvider();
        NFT_Token contract = NFT_Token.deploy(web3j, credentials, gasProvider, "Sulaiman NFT Marketplace", "SUCF").send();

        return contract.getContractAddress();
    }
}
