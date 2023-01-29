package com.example.springbootboostrap.dto.response.authentication;

import com.example.springbootboostrap.dto.response.BaseResponse;
import lombok.Data;

@Data
public class LoginResponse extends BaseResponse {

    String token;

    String expirationDate;
}
