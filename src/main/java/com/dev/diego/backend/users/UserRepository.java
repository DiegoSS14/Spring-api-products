package com.dev.diego.backend.users;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, UUID>{
    User findByEmail(String email);
}
