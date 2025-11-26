package com.example.learning_security_evening.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.learning_security_evening.model.AppUser;
@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByUsername(String username);

}
