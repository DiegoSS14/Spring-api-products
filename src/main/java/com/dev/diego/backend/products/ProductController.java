package com.dev.diego.backend.products;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.diego.backend.products.dto.ProductDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @GetMapping()
    public List<ProductDTO> getAll(@AuthenticationPrincipal UserDetails userDetails) {
        return productService.getAll(userDetails.getUsername());
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable(name = "id") Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        return productService.findById(id, userDetails.getUsername());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO product, @AuthenticationPrincipal UserDetails userDetails) {
        ProductDTO productEntity = productService.create(product, userDetails.getUsername());
            Integer id = null;
            if (productEntity != null && productEntity.getId() != null) {
                id = productEntity.getId().orElseThrow(() -> new IllegalStateException("Created product id missing"));
            }
            return ResponseEntity.created(URI.create("/products/" + id)).body(productEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @RequestBody ProductDTO product, @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(productService.editProduct(id, product, userDetails.getUsername()));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable Integer id, @AuthenticationPrincipal UserDetails userDetails) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
