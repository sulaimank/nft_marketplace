package com.skarmali.sk_nft_marketplace.repository;

import com.skarmali.sk_nft_marketplace.model.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CollectionRepository extends MongoRepository<Collection, String> {
    Optional<Collection> findByName(String name);
}