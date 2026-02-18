package com.emi.mentorhub.service.impl;

import com.emi.mentorhub.dto.UserRequest;
import com.emi.mentorhub.dto.UserResponse;
import com.emi.mentorhub.entity.User;
import com.emi.mentorhub.exception.EmailAlreadyExistsException;
import com.emi.mentorhub.repository.UserRepository;
import com.emi.mentorhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
public UserResponse createUser(UserRequest request) {

    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
    throw new EmailAlreadyExistsException("Email already registered");
}


    User user = User.builder()
            .name(request.getName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .active(true) // ← AGREGAR ESTO
            .createdAt(java.time.LocalDateTime.now()) // ← AGREGAR ESTO
            .build();

    User saved = userRepository.save(user);

    return UserResponse.builder()
            .id(saved.getId())
            .name(saved.getName())
            .email(saved.getEmail())
            .role(saved.getRole())
            .active(saved.getActive())
            .createdAt(saved.getCreatedAt())
            .build();
}


    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .active(user.getActive())
                        .createdAt(user.getCreatedAt())
                        .build()
                )
                .toList();
    }
}
