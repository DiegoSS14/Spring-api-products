package com.dev.diego.backend.products.mapper;

import java.sql.Date;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.dev.diego.backend.products.Product;
import com.dev.diego.backend.products.dto.ProductDTO;
import com.dev.diego.backend.users.User;
import com.dev.diego.backend.users.dto.UserRegistredDTO;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    @Mappings({
        @Mapping(source = "id", target = "id", qualifiedByName = "integerToOptional"),
        @Mapping(source = "insertIn", target = "insertIn", qualifiedByName = "dateToOptional"),
        @Mapping(source = "user", target = "user")
    })
    public ProductDTO entityToDTO(Product product);

    @Mappings({
        @Mapping(source = "id", target = "id", qualifiedByName = "optionalToInteger"),
        @Mapping(source = "insertIn", target = "insertIn", qualifiedByName = "optionalToDate")
    })
    public Product DTOtoEntity(ProductDTO productDTO);

    @Named(value = "optionalToInteger")
    public default Integer optionalToInteger(Optional<Integer> id) {
        return id == null ? null : id.orElse(null);
    }

    @Named(value = "optionalToDate")
    public default Date optionalToDate(Optional<Date> date) {
        return date == null ? null : date.orElse(null);
    }

    @Named(value = "integerToOptional")
    public default Optional<Integer> integerToOptional(Integer id) {
        return Optional.ofNullable(id);

    }
    @Named(value = "dateToOptional")
    public default Optional<Date> dateToOptional(Date date) {
        return Optional.ofNullable(date);
    }

    public default UserRegistredDTO userToUserRegistredDTO(User user) {
        if (user == null) return null;
        return new UserRegistredDTO(user.getName(), user.getEmail());
    }
}
