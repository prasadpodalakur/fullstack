package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Role;

import java.util.Optional;

public interface AuthRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}