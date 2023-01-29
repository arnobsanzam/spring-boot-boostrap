package com.example.springbootboostrap.dto.response.user;

import com.example.springbootboostrap.dto.response.BaseResponse;
import lombok.Data;

@Data
public class UserCreationResponse extends BaseResponse {
    public String username;
}
