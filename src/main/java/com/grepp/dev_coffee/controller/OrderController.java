package com.grepp.dev_coffee.controller;


import com.grepp.dev_coffee.model.dto.OrderDTO;
import com.grepp.dev_coffee.model.dto.ProductDTO;
import com.grepp.dev_coffee.model.service.OrderService;
import com.grepp.dev_coffee.model.service.ProductService;
//import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@Tag(name = "OrderController", description = "User related operations")
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    //@Operation(summary = "Get users", description = "Get a list of all users")
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
        UUID id = orderService.createOrder(orderDTO);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> list(@PathVariable String email){
        List<OrderDTO> orderDTOS = orderService.findAll(email);
        return  ResponseEntity.ok().body(orderDTOS);
    }

    @GetMapping("/read/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable UUID orderId){
        OrderDTO orderDTO = orderService.findById(orderId);
        return ResponseEntity.ok().body(orderDTO);
    }

    @PutMapping("/{email}")
    public ResponseEntity<?> update(@PathVariable String email,@RequestBody OrderDTO orderDTO){
        UUID id = orderService.updateOrder(email,orderDTO);
        return ResponseEntity.ok().body(id);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> delete(@PathVariable String email){
        UUID id=  orderService.cancelOrder(email);
        return  ResponseEntity.ok().body(id);
    }

}
