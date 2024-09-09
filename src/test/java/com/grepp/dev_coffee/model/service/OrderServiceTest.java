/*
package com.grepp.dev_coffee.model.service;

import com.grepp.dev_coffee.model.dto.OrderDTO;
import com.grepp.dev_coffee.model.dto.OrderItemDTO;
import com.grepp.dev_coffee.model.dto.ProductDTO;
import com.grepp.dev_coffee.model.entity.OrderStatus;
import com.grepp.dev_coffee.model.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void createOrder() {

        OrderDTO orderDTO = OrderDTO.builder()
                .order_id(1)
                .email("jlsowltn2000")
                .address("인천광역시")
                .postcode("234234")
                .orderStatus(OrderStatus.ORDERED)
                .orderItemsDtos(List.of(
                        OrderItemDTO.builder()
                                .price(1000L)
                                .quantity(100)
                                .category("dd")
                                .productDTO(
                                        ProductDTO.builder()
                                                .productName("아메리카노")
                                                .category("커피")
                                                .price(1000)
                                                .stock(100)
                                                .description("고소한 아메리카노입니다.")
                                                .build()
                                )
                                .build(),
                        OrderItemDTO.builder()
                                .price(1000L)
                                .quantity(100)
                                .category("dd")
                                .productDTO(
                                        ProductDTO.builder()
                                                .productName("아이스라떼")
                                                .category("커피")
                                                .price(1000)
                                                .stock(100)
                                                .description("달달한 라떼입니다.")
                                                .build()
                                )
                                .build()

                ))
                .build();
        // When
        int order_id = orderService.createOrder(orderDTO);
        log.info("{}",order_id);
    }

}*/
