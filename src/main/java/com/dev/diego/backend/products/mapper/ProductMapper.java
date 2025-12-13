package com.dev.diego.backend.products.mapper;

import java.sql.Date;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.dev.diego.backend.products.Product;
import com.dev.diego.backend.products.dto.ProductDTO;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
        // será preenchido pelo @CurrentTimestamp / banco
    @Mappings({
        @Mapping(source = "id", target = "id", qualifiedByName = "integerToOptional"),
        @Mapping(source = "insertIn", target = "insertIn", qualifiedByName = "dateToOptional")
    })
    public ProductDTO entityToDTO(Product product);

    @Mappings({
        @Mapping(source = "id", target = "id", qualifiedByName = "optionalToInteger"),
        @Mapping(source = "insertIn", target = "insertIn", qualifiedByName = "optionalToDate")
    })
    public Product DTOtoEntity(ProductDTO productDTO);

    @Named(value = "optionalToInteger")
    public default Integer optionalToInteger(Optional<Integer> id) {
        return id.get();
    }

    @Named(value = "optionalToDate")
    public default Date optionalToDate(Optional<Date> date) {
        return date.get();
    }

    @Named(value = "integerToOptional")
    public default Optional<Integer> integerToOptional(Integer id) {
        return Optional.ofNullable(id);

    }
    @Named(value = "dateToOptional")
    public default Optional<Date> dateToOptional(Date date) {
        return Optional.ofNullable(date);
    }
}
