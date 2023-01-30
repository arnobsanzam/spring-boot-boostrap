package com.example.springbootboostrap.service.user;

import com.example.springbootboostrap.dto.request.user.UserCreationRequest;
import com.example.springbootboostrap.dto.response.user.UserDetailsResponse;
import com.example.springbootboostrap.dto.response.user.UserListResponse;
import com.example.springbootboostrap.entity.User;

public interface UserService {

    UserDetailsResponse createUser(UserCreationRequest request);

    User getUserByUsername(String username);

    void saveUser(User user);

    UserListResponse getAllUsers();

    UserDetailsResponse getUserById(Long id);
}
