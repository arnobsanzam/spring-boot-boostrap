package com.example.springbootboostrap.mapper;

import com.example.springbootboostrap.dto.response.authentication.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper extends BaseMapper {

    public LoginResponse toLoginResponse(String token) {
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        return  response;
    }
}
