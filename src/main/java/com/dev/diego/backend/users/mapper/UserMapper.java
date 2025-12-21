package com.dev.diego.backend.users.mapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.dev.diego.backend.products.Product;
import com.dev.diego.backend.products.dto.ProductDTO;
import com.dev.diego.backend.products.mapper.ProductMapper;
import com.dev.diego.backend.users.User;
import com.dev.diego.backend.users.dto.UserDTO;
import com.dev.diego.backend.users.dto.UserLoggedDTO;
import com.dev.diego.backend.users.dto.UserLoginDTO;
import com.dev.diego.backend.users.dto.UserRegisterDTO;
import com.dev.diego.backend.users.dto.UserRegistredDTO;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {ProductMapper.class})
public interface UserMapper {

    public User registerToUser(UserRegisterDTO user);

    public UserRegistredDTO userToRegistred(User user);

    public UserRegistredDTO registredToRegister(UserRegistredDTO userRegisterDTO);

    public UserLoggedDTO loginToLogged(UserLoginDTO userLoginDTO);

    public UserLoginDTO loggedToLogin(UserLoggedDTO userLoggedDTO);

    @Mappings({
            @Mapping(source = "id", target = "id", qualifiedByName = "uuidToOptional"),
            @Mapping(source = "senha", target = "senha", qualifiedByName = "stringToOptional"),
            @Mapping(source = "products", target = "products")
    })
    public UserDTO entityToDTO(User user);

    @Mappings({
            @Mapping(source = "id", target = "id", qualifiedByName = "optionalToUUID"),
            @Mapping(source = "senha", target = "senha", qualifiedByName = "optionalToString"),
            @Mapping(source = "products", target = "products")
    })
    public User dtoToEntity(UserDTO user);

    public List<ProductDTO> listEntityToListDTO(List<Product> products);

    public List<Product> listDtoToListEntity(List<ProductDTO> products);

    @Named(value = "uuidToOptional")
    public default Optional<UUID> uuidToOptional(UUID uuid) {
        return Optional.ofNullable(uuid);
    }

    @Named(value = "optionalToUUID")
    public default UUID optionalToUUID(Optional<UUID> uuid) {
        return uuid.get();
    }

    @Named(value = "stringToOptional")
    public default Optional<String> stringToOptional(String senha) {
        return Optional.ofNullable(senha);
    }

    @Named(value = "optionalToString")
    public default String optionalToString(Optional<String> senha) {
        return senha.get();
    }
}
