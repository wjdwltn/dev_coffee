package com.grepp.dev_coffee.model.dto;

import com.grepp.dev_coffee.model.entity.Order;
import com.grepp.dev_coffee.model.entity.Product;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private UUID productId;
    private String productName;
    private String category;
    private long price;
    private int stock;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
