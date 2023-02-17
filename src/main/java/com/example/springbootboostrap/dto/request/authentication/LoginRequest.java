package com.example.springbootboostrap.dto.request.authentication;

import com.example.springbootboostrap.dto.request.BaseRequest;
import lombok.Data;

@Data
public class LoginRequest extends BaseRequest {

    private final String username;

    private String password;
}
