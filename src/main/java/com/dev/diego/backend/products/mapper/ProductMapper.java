package com.dev.diego.backend.products.mapper;

import org.mapstruct.Mapper;

import com.dev.diego.backend.products.Product;
import com.dev.diego.backend.products.dto.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {    // será preenchido pelo @CurrentTimestamp / banco
    public ProductDTO entityToDTO(Product product);
    public Product DTOtoEntity(ProductDTO productDTO);
}
