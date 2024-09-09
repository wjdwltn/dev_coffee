package com.grepp.dev_coffee.converter;


import com.grepp.dev_coffee.model.dto.OrderItemDTO;
import com.grepp.dev_coffee.model.dto.ProductDTO;
import com.grepp.dev_coffee.model.entity.*;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.grepp.dev_coffee.model.entity.Order;
import com.grepp.dev_coffee.model.dto.OrderDTO;

@Component
public class OrderConverter {
    public Order convertOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderId(orderDTO.getOrderId());
        order.setEmail(orderDTO.getEmail());
        order.setAddress(orderDTO.getAddress());
        order.setPostcode(orderDTO.getPostcode());

        order.setOrderStatus(OrderStatus.ORDERED);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        this.convertOrderItems(orderDTO).forEach(order::addOrderItem);

        return order;
    }

// OrderDto로 들어온 OrderItemDTO list를 OrderItem(엔티티) list로 변환해주기
    private List<OrderItem> convertOrderItems(OrderDTO orderDTO) {
        return orderDTO.getOrderItemsDtos().stream()
                .map(orderItemDto -> {
                    OrderItem orderItem = new OrderItem();

                    orderItem.setPrice(orderItemDto.getPrice());
                    orderItem.setQuantity(orderItemDto.getQuantity());
                    orderItem.setCategory(orderItemDto.getCategory());

                    orderItem.setCreatedAt(LocalDateTime.now());
                    orderItem.setUpdatedAt(LocalDateTime.now());
                   orderItem.setProduct(this.convertProduct(orderItemDto.getProductDTO()));
                    return orderItem;
                })
                .collect(Collectors.toList());
    }
    public Product convertProduct(ProductDTO productDTO) {
        Product product = new Product();

        product.setProductId((productDTO.getProductId()));
        product.setProductName(productDTO.getProductName());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        //product.setStock(productDTO.getStock());
        product.setDescription(productDTO.getDescription());

        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        return product;
    }

    public OrderDTO convertOrderDto (Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .email(order.getEmail())
                .address(order.getAddress())
                .postcode(order.getPostcode())
                .orderStatus(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .orderItemsDtos(order.getOrderItems().stream()
                        .map(this::convertOrderItemDto)
                        .collect(Collectors.toList())
                )
                .build();
    }


    public OrderItemDTO convertOrderItemDto (OrderItem orderItem) {
        return OrderItemDTO.builder()
                .seq(orderItem.getSeq())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .createdAt(orderItem.getCreatedAt())
                .updatedAt(orderItem.getUpdatedAt())
                .productDTO(this.convertProductDto(orderItem.getProduct()))
                .build();
    }

    public ProductDTO convertProductDto (Product product) {

        return ProductDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();

    }

}




