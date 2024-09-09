package com.grepp.dev_coffee.model.dto;

import com.grepp.dev_coffee.model.entity.*;
import com.grepp.dev_coffee.model.entity.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class OrderDTO {
    private UUID orderId;
    private String email;
    private String address;
    private String postcode;
    private OrderStatus orderStatus;
    private List<OrderItemDTO> orderItemsDtos;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
