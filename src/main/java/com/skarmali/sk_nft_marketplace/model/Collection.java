package com.skarmali.sk_nft_marketplace.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "collections")
@Getter
@Setter
public class Collection {
    @Id
    private String id;
    private String name;
    private String collection;
    private String category;
    private String url;
    private String description;
    private String imageUrl;
}
