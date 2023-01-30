package com.example.springbootboostrap.mapper;

import com.example.springbootboostrap.dto.response.authentication.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper extends BaseMapper {

    public LoginResponse toLoginResponse(String token, HttpStatus httpStatus) {
        LoginResponse response = new LoginResponse();
        response.setHttpStatus(httpStatus);
        response.setToken(token);
        return  response;
    }
}
