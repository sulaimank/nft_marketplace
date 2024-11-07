package com.skarmali.sk_nft_marketplace.repository;

import com.skarmali.sk_nft_marketplace.model.CollectionUrl;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollectionUrlRepository extends MongoRepository<CollectionUrl, String> {
}