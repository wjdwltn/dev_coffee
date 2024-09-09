package com.grepp.dev_coffee.model.service;

import com.grepp.dev_coffee.converter.OrderConverter;
import com.grepp.dev_coffee.model.dto.ProductDTO;
import com.grepp.dev_coffee.model.entity.Product;
import com.grepp.dev_coffee.model.repository.ProductRepository;
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
public class ProductService {

    private final OrderConverter orderConverter;


    private final ProductRepository productRepository;

    //커피 등록
    public UUID createProduct(ProductDTO productDTO){
        Product savedProduct = productRepository.save(orderConverter.convertProduct(productDTO));
        return savedProduct.getProductId();
    }

    //커피 수정
    public UUID updateProduct(UUID productId,ProductDTO productDTO){
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new NoSuchElementException("커피를 찾을 수 없습니다."));

        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setStock(productDTO.getStock());
        product.setDescription(productDTO.getDescription());

        return productRepository.save(product).getProductId();

    }

    //커피 삭제
    public UUID cancelProduct(UUID productId) {
        // 상품 조회
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new NoSuchElementException("상품을 찾을 수 없습니다."));

         productRepository.deleteByProductId(productId);
        return product.getProductId();
    }


   /* //커피 목록 조회
    public Page<ProductDTO> findAll(Pageable pageable){
        return productRepository.findAll(pageable)
                .map(orderConverter::convertProductDto);
    }*/
   //커피 목록 조회
   public List<ProductDTO> findAll(){
       List<Product> products = productRepository.findAll();
       System.out.println("Found products: " + products); // 디버깅 로그 추가
       return products.stream()
               .map(orderConverter::convertProductDto)
               .collect(Collectors.toList());
   }


    //커피 상세 조회
    public ProductDTO getProduct(UUID productId){
        return productRepository.findByProductId(productId)
                .map(this::convertDto)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    //커피 재고 차감
    public void removeProduct(UUID productId, int quantity){
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new NoSuchElementException("상품을 찾을 수 없습니다."));

        if(product.getStock() <quantity){
            throw new IllegalStateException("재고가 부족합니다.");
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);
    }

    private ProductDTO convertDto (Product product) {

        return ProductDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();}

    }




