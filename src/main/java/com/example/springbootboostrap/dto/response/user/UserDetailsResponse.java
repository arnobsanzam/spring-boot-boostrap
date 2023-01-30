package com.example.springbootboostrap.dto.response.user;

import com.example.springbootboostrap.dto.response.BaseResponse;
import lombok.Data;

@Data
public class UserDetailsResponse extends BaseResponse {

    public String username;
}
