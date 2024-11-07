package com.skarmali.sk_nft_marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableMongoRepositories(basePackages = "com.skarmali.sk_nft_marketplace.repository")
public class SkNftMarketplaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkNftMarketplaceApplication.class, args);
    }

}
