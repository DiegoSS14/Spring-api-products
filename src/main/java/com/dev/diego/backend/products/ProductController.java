package com.dev.diego.backend.products;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @GetMapping()
    public List<ProductModel> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductModel getById(@PathVariable(name = "id") Integer id) {
        return productService.findById(id);
    }
}
