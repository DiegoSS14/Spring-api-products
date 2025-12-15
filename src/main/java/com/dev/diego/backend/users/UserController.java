package com.dev.diego.backend.users;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.diego.backend.users.dto.UserDTO;
import com.dev.diego.backend.users.dto.UserRegisterDTO;
import com.dev.diego.backend.users.dto.UserRegistredDTO;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class UserController {
    
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getMethodName(@PathVariable UUID id) {
        return ResponseEntity.ok().body(userService.getById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistredDTO> postMethodName(@RequestBody UserRegisterDTO user) {
        return ResponseEntity.status(201).body(userService.userRegister(user));
    }
    
    
}
