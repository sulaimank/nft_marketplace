package com.skarmali.sk_nft_marketplace.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "collection_urls")
@Getter
@Setter
public class CollectionUrl {
    public CollectionUrl(String id, String url) {
        this.id = id;
        this.url = url;
    }

    @Id
    private String id;
    private String url;
}