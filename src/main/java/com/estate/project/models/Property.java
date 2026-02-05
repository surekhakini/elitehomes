package com.estate.project.models;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String city;
    private String type;
    private double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "seller_username")
    private String sellerUsername;

    private int views = 0;

    @Column(name = "wishlist_count")
    private int wishlistCount = 0;

    @Column(name = "contact_count")
    private int contactCount = 0;

    private boolean active = true;
}
