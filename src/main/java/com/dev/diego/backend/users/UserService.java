package com.dev.diego.backend.users;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dev.diego.backend.users.dto.UserDTO;
import com.dev.diego.backend.users.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAll() {
        List<UserDTO> users = userRepository.findAll().stream().map(userMapper::entityToDTO).toList();
        return users;
    }

    public UserDTO getById(UUID id) {
        return userMapper.entityToDTO(userRepository.findById(id).get());
    }
}