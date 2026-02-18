package com.emi.mentorhub.service;

import com.emi.mentorhub.dto.UserRequest;
import com.emi.mentorhub.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    List<UserResponse> getAllUsers();
}
