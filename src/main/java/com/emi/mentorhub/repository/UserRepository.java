package com.emi.mentorhub.repository;

import com.emi.mentorhub.entity.Role;
import com.emi.mentorhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    long countByRole(Role role);
}
