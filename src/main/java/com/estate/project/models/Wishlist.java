package com.estate.project.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private Long propertyId;
    private String title;
    private String city;
    private String description;
    private String price;
    private String imageUrl;

    public LocalDateTime createdAt;

}
