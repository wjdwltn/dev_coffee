package com.grepp.dev_coffee.model.service;


import com.grepp.dev_coffee.converter.OrderConverter;
import com.grepp.dev_coffee.model.dto.OrderDTO;
import com.grepp.dev_coffee.model.entity.Order;
import com.grepp.dev_coffee.model.entity.OrderItem;
import com.grepp.dev_coffee.model.entity.OrderStatus;
import com.grepp.dev_coffee.model.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {


    private final OrderConverter orderConverter;

    private final OrderRepository orderRepository;

    private final ProductService productService;


    //주문 생성
    public UUID createOrder(OrderDTO orderDTO){
        Order order =orderConverter.convertOrder(orderDTO);

        //재고처리
        List<OrderItem> orderItems = order.getOrderItems();
        for(OrderItem orderItem : orderItems){
            System.out.println("가격은? ?!!!!!!!!!!!!!!!!!!!!!!!"+orderItem.getPrice());
            System.out.println("아이디뭐야? ?!!!!!!!!!!!!!!!!!!!!!!! "+orderItem.getProduct().getProductId());
            productService.removeProduct(orderItem.getProduct().getProductId(), orderItem.getQuantity());
        }

        Order saveOrder = orderRepository.save(order);
        return saveOrder.getOrderId();
    }

    /*//주문 목록 조회(본인 이메일로만)
    public Page<OrderDTO> findAll(Pageable pageable, String email){
        return orderRepository.findAllByEmailOrderByCreatedAtDesc(pageable,email)
                .map(orderConverter::convertOrderDto);
    }*/

    //주문 목록 조회(본인 이메일로만)
    public List<OrderDTO> findAll( String email){
        return orderRepository.findAllByEmailOrderByCreatedAtDesc(email).stream()
                .map(orderConverter::convertOrderDto)
                .collect(Collectors.toList());
    }

    //주문 상세 내역 확인 (아이디)
    public OrderDTO findById(UUID orderId){
        return orderRepository.findByOrderId(orderId)
                .map(orderConverter::convertOrderDto)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    //주문상태가 배송 전일때만 주문을 수정할 수 있다. (이메일,주소 등)
    public UUID updateOrder(String email, OrderDTO orderDTO){
        Order order = orderRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("주문을 찾을 수 없습니다."));

        System.out.println("@@@@@@@Current order status: " + order.getOrderStatus()); // 디버깅
        System.out.println("@@@@@@@OrderStatus.ORDERED " + OrderStatus.ORDERED); // 디버깅

        if(order.getOrderStatus() == OrderStatus.ORDERED){
            order.setAddress(orderDTO.getAddress());
            order.setPostcode(orderDTO.getPostcode());
            //order.setOrderStatus(order.getOrderStatus());
            return order.getOrderId();
        }else {
            // 주문 상태가 'ORDERED'가 아닐 경우 예외를 던지거나 적절한 처리를 함
            throw new IllegalStateException("현재 상태에서는 주문을 수정할 수 없습니다.");
            }

    }

    //주문 취소
    public UUID cancelOrder(String email) {
        // 주문 조회
        Order order = orderRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("주문을 찾을 수 없습니다."));

        if (order.getOrderStatus() != OrderStatus.ORDERED) {
            throw new IllegalStateException("현재 상태에서는 주문을 취소할 수 없습니다.");
        }

        order.setOrderStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
        return order.getOrderId();
    }
}
