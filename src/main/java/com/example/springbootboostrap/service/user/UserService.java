package com.example.springbootboostrap.service.user;

import com.example.springbootboostrap.dto.request.user.UserCreationRequest;
import com.example.springbootboostrap.dto.response.user.UserCreationResponse;
import com.example.springbootboostrap.dto.response.user.UserListResponse;
import com.example.springbootboostrap.entity.User;
import com.example.springbootboostrap.record.UserInfo;

public interface UserService {

    UserCreationResponse createUser(UserCreationRequest request);

    User getUserByUsername(String username);

    UserInfo getUserInfoByUsername(String username);

    void saveUser(User user);

    UserListResponse getAllUsers();
}
