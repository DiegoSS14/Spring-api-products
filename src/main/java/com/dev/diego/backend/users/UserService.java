package com.dev.diego.backend.users;

import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.diego.backend.exceptions.UserAlreadyExists;
import com.dev.diego.backend.exceptions.UserNotExists;
import com.dev.diego.backend.users.dto.UserDTO;
import com.dev.diego.backend.users.dto.UserLoggedDTO;
import com.dev.diego.backend.users.dto.UserLoginDTO;
import com.dev.diego.backend.users.dto.UserRegisterDTO;
import com.dev.diego.backend.users.dto.UserRegistredDTO;
import com.dev.diego.backend.users.mapper.UserMapper;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(
            UserRepository userRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder,
            @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

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
        saveUser.setSenha(passwordEncoder.encode(saveUser.getSenha()));
        userRepository.saveAndFlush(saveUser);
        return userMapper.userToRegistred(saveUser);
    }

    public UserLoggedDTO login(UserLoginDTO userLogin) {
        User user = userRepository.findByEmail(userLogin.getEmail());
        if (user == null) {
            throw new UserNotExists();
        }
        UsernamePasswordAuthenticationToken emailPassword = new UsernamePasswordAuthenticationToken(
                userLogin.getEmail(), userLogin.getSenha());
        authenticationManager.authenticate(emailPassword);
        UserLoggedDTO userLogged = userMapper.loginToLogged(userLogin);
        userLogged.setToken("Token...");
        return userLogged;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }
}