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

    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream().map(product -> productMapper.entityToDTO(product)).toList();
    }

    public ProductDTO findById(Integer id) {
        Product productEntity = productRepository.findById(id).orElseThrow(ProductNotFound::new);
        return productMapper.entityToDTO(productEntity);
    }

    public ProductDTO create(ProductDTO product) {
        Product productEntity = productRepository.saveAndFlush(productMapper.DTOtoEntity(product));
        return productMapper.entityToDTO(productEntity);
    }

    public ProductDTO editProduct(Integer id , ProductDTO productEdit) {
        Product originalProduct = productMapper.DTOtoEntity(this.findById(id));
        if (productEdit.getName() != null) {
            originalProduct.setName(productEdit.getName());
        }
        if (productEdit.getDescription() != null) {
            originalProduct.setDescription(productEdit.getDescription());
        }
        if (productEdit.getPrice() != null) {
            originalProduct.setPrice(productEdit.getPrice());
        }
        if (productEdit.getCategory() != null) {
            originalProduct.setCategory(productEdit.getCategory());
        }
        productRepository.saveAndFlush(originalProduct);

        return productMapper.entityToDTO(originalProduct);
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
