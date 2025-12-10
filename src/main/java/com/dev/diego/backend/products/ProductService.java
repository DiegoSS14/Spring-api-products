package com.dev.diego.backend.products;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dev.diego.backend.exceptions.ProductNotFound;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductModel> getAll() {
        return productRepository.findAll();
    }

    public ProductModel findById(Integer id) {
        return productRepository.findById(id).orElseThrow(ProductNotFound::new);
    }
}
