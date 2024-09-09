package com.grepp.dev_coffee.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name="products")
public class Product {
    @Id @GeneratedValue
    @Column(name = "product_id")
    private UUID productId;

    private String productName;
    private String category;
    private long price;
    private int stock;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product() {
    }
}
