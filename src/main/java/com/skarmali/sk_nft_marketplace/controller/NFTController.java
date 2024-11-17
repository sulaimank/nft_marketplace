package com.skarmali.sk_nft_marketplace.controller;

import com.skarmali.sk_nft_marketplace.model.Collection;
import com.skarmali.sk_nft_marketplace.model.CollectionUrl;
import com.skarmali.sk_nft_marketplace.repository.CollectionRepository;
import com.skarmali.sk_nft_marketplace.repository.CollectionUrlRepository;
import com.skarmali.sk_nft_marketplace.service.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
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

    @Autowired
    private NFTService nftService;

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

    @PostMapping("/sell/{id}")
    public ResponseEntity<String> sellNFT(@PathVariable String id) {
        try {
            String transactionHash = nftService.sellNFT(id);
            return ResponseEntity.ok(transactionHash);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/buy/{id}")
    public ResponseEntity<String> buyNFT(@PathVariable String id) {
        try {
            String transactionHash = nftService.buyNFT(id);
            return ResponseEntity.ok(transactionHash);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
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
        return nftService.deployContract();
    }

    @GetMapping("/balance")
    public ResponseEntity<BigInteger> getBalance() throws Exception {
        BigInteger balance = nftService.getBalance();
        return ResponseEntity.ok(balance);
    }
}
