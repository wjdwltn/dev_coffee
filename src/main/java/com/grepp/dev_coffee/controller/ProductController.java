package com.grepp.dev_coffee.controller;

import com.grepp.dev_coffee.model.dto.ProductDTO;
import com.grepp.dev_coffee.model.entity.Product;
import com.grepp.dev_coffee.model.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        UUID id = productService.createProduct(productDTO);
        return ResponseEntity.ok().body(id);
    }

    /*@GetMapping
    public Page<?> list(@PageableDefault(size=10, sort="updatedAt", direction = Sort.Direction.DESC) Pageable pageable){
        Page<ProductDTO> productDTOS = productService.findAll(pageable);
        return productDTOS;
    }*/
    @GetMapping
    public ResponseEntity<?> list(){
        List<ProductDTO> productDTOS = productService.findAll();
        return  ResponseEntity.ok().body(productDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable UUID id){
        ProductDTO productDTO = productService.getProduct(id);
        return ResponseEntity.ok().body(productDTO);
    }
}
