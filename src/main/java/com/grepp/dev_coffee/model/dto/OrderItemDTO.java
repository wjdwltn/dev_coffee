package com.grepp.dev_coffee.model.dto;

import com.grepp.dev_coffee.model.entity.Order;
import com.grepp.dev_coffee.model.entity.OrderItem;
import com.grepp.dev_coffee.model.entity.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long seq;
    private Order order;
    private Long price;
    private int quantity;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ProductDTO productDTO;


}
