package com.grepp.dev_coffee.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Getter @Setter
@Table(name="orders")
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private UUID orderId;

    private String email;
    private String address;
    private String postcode;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
    }

    public Order() {
    }
}
