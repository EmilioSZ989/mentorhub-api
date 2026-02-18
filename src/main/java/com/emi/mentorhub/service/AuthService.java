package com.emi.mentorhub.service;

import com.emi.mentorhub.dto.AuthResponse;
import com.emi.mentorhub.dto.LoginRequest;
import com.emi.mentorhub.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
