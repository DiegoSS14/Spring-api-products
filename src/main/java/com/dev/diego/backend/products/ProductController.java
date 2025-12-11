package com.dev.diego.backend.products;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.diego.backend.products.dto.ProductDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @GetMapping()
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable(name = "id") Integer id) {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO product) {
        Product productEntity = productService.create(product);
        return ResponseEntity.created(URI.create("/" + productEntity.getId())).body(productEntity);
    }
}
