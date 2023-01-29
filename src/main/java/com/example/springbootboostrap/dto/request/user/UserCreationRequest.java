package com.example.springbootboostrap.dto.request.user;

import com.example.springbootboostrap.dto.request.BaseRequest;
import lombok.Data;

@Data
public class UserCreationRequest extends BaseRequest {

    private final String username;

    private final String password;
}
