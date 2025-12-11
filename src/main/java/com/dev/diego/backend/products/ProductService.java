package com.dev.diego.backend.products;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.diego.backend.exceptions.ProductNotFound;
import com.dev.diego.backend.products.dto.ProductDTO;
import com.dev.diego.backend.products.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        return productRepository.findById(id).orElseThrow(ProductNotFound::new);
    }

    public Product create(ProductDTO product) {
        return productRepository.save(productMapper.DTOtoEntity(product));
    }
}
