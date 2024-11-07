package com.skarmali.sk_nft_marketplace.config;

import com.skarmali.sk_nft_marketplace.model.CollectionUrl;
import com.skarmali.sk_nft_marketplace.repository.CollectionUrlRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initCollectionUrls(CollectionUrlRepository collectionUrlRepository) {
        return args -> {
            List<CollectionUrl> initialUrls = Arrays.asList(
                    new CollectionUrl("1", "https://api.opensea.io/api/v2/collections/cryptopunks"),
                    new CollectionUrl("2", "https://api.opensea.io/api/v2/collections/corpo-real-by-claire-silver"),
                    new CollectionUrl("3", "https://api.opensea.io/api/v2/collections/boredapeyachtclub")
                    // Add more URLs as needed
            );

            initialUrls.forEach(url -> {
                if (!collectionUrlRepository.existsById(url.getId())) {
                    collectionUrlRepository.save(url);
                }
            });
        };
    }
}