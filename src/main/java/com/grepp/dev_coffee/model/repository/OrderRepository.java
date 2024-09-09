package com.grepp.dev_coffee.model.repository;

import com.grepp.dev_coffee.model.dto.OrderDTO;
import com.grepp.dev_coffee.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByEmail(String email);
    //Page<Order> findAllByEmailOrderByCreatedAtDesc(Pageable pageable, String email);
    List<Order> findAllByEmailOrderByCreatedAtDesc(String email);
}
