package com.example.springbootboostrap.service.authentication;

import com.example.springbootboostrap.dto.request.authentication.LoginRequest;
import com.example.springbootboostrap.dto.response.authentication.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest request);
}
