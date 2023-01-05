package com.example.demouser.repository;

import com.example.demouser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail (String email);

    Optional<User> findById(UUID id);

}
