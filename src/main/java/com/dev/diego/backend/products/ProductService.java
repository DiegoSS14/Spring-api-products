package com.dev.diego.backend.products;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dev.diego.backend.exceptions.ProductNotFound;
import com.dev.diego.backend.products.dto.ProductDTO;
import com.dev.diego.backend.products.mapper.ProductMapper;
import com.dev.diego.backend.users.User;
import com.dev.diego.backend.users.UserRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    public final UserRepository userRepository;

    public List<ProductDTO> getAll(String email) {
        User user = userRepository.findByEmail(email);
        UUID userId = user.getId();

        return productRepository.findByUserId(userId).stream().map(product -> productMapper.entityToDTO(product)).toList();
    }

    public ProductDTO findById(Integer id, String email) {
        User user = userRepository.findByEmail(email);
        Product product = productRepository.findByIdAndUserId(id, user.getId()).orElseThrow(ProductNotFound::new);
        return productMapper.entityToDTO(product);
    }

    public ProductDTO create(ProductDTO productDTO, String email) {
        User user = userRepository.findByEmail(email);
        Product productEntity = productMapper.DTOtoEntity(productDTO);
        productEntity.setUser(user);
        Product saved = productRepository.saveAndFlush(productEntity);

        System.out.println("id saved: " + saved.getId());
        return productMapper.entityToDTO(saved);
    }

    public ProductDTO editProduct(Integer id , ProductDTO productEdit, String email) {
        Product originalProduct = productMapper.DTOtoEntity(this.findById(id, email));
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
