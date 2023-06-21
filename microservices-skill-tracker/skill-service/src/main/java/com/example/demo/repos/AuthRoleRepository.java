package com.example.demo.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Role;

public interface AuthRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}