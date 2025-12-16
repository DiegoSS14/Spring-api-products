package com.dev.diego.backend.users;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.diego.backend.exceptions.UserAlreadyExists;
import com.dev.diego.backend.users.dto.UserDTO;
import com.dev.diego.backend.users.dto.UserRegisterDTO;
import com.dev.diego.backend.users.dto.UserRegistredDTO;
import com.dev.diego.backend.users.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> getAll() {
        List<UserDTO> users = userRepository.findAll().stream().map(userMapper::entityToDTO).toList();
        return users;
    }

    public UserDTO getById(UUID id) {
        return userMapper.entityToDTO(userRepository.findById(id).get());
    }

    public UserRegistredDTO register(UserRegisterDTO userRegisterDTO) {
        User findUser = userRepository.findByEmail(userRegisterDTO.getEmail());
        if (findUser != null) {
            throw new UserAlreadyExists();
        }
        User saveUser = userMapper.registerToUser(userRegisterDTO);
        saveUser.setPassword(passwordEncoder.encode(saveUser.getPassword()));
        userRepository.saveAndFlush(saveUser);
        return userMapper.userToRegistred(saveUser);
    }
}