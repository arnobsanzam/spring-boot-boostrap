package com.example.springbootboostrap.dto.response.user;

import com.example.springbootboostrap.dto.response.BaseResponse;
import lombok.Data;

import java.util.List;

@Data
public class UserListResponse extends BaseResponse {
    List<UserCreationResponse> users;
}
