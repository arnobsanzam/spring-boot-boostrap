package com.example.springbootboostrap.repository;

import com.example.springbootboostrap.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findFirstByUsername(String username);
    User save(User user);
}
