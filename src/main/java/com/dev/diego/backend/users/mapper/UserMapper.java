package com.dev.diego.backend.users.mapper;

import java.util.Optional;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.dev.diego.backend.users.User;
import com.dev.diego.backend.users.dto.UserDTO;
import com.dev.diego.backend.users.dto.UserRegisterDTO;
import com.dev.diego.backend.users.dto.UserRegistredDTO;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {


    public UserRegistredDTO registerToRegistred(UserRegisterDTO user);
    public User registerToUser(UserRegisterDTO user);
    public UserRegistredDTO userToRegistred(User user);
    public UserRegistredDTO registredToRegister(UserRegistredDTO userRegisterDTO);

    @Mappings({
        @Mapping(source = "id", target = "id", qualifiedByName = "uuidToOptional"),
        @Mapping(source = "password", target = "password", qualifiedByName = "stringToOptional")
    })
    public UserDTO entityToDTO(User user);

    @Mappings({
        @Mapping(source = "id", target = "id", qualifiedByName = "optionalToUUID"),
        @Mapping(source = "password", target = "password", qualifiedByName = "optionalToString")
    })
    public User entityToDTO(UserDTO user);

    @Named(value = "uuidToOptional")
    public default Optional<UUID> uuidToOptional(UUID uuid) {
        return Optional.ofNullable(uuid);
    }

    @Named(value = "optionalToUUID")
    public default UUID optionalToUUID(Optional<UUID> uuid) {
        return uuid.get();
    }

    @Named(value = "stringToOptional")
    public default Optional<String> stringToOptional(String password) {
        return Optional.ofNullable(password);
    }

    @Named(value = "optionalToString")
    public default String optionalToString(Optional<String> password) {
        return password.get();
    }
}
