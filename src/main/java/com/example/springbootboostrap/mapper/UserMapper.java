package com.example.springbootboostrap.mapper;

import com.example.springbootboostrap.appenum.StatusType;
import com.example.springbootboostrap.dto.request.user.UserCreationRequest;
import com.example.springbootboostrap.dto.response.user.UserCreationResponse;
import com.example.springbootboostrap.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper extends BaseMapper {
    private final ModelMapper modelMapper;

    public User toEntity(UserCreationRequest request) {
        return modelMapper.map(request, User.class);
    }

    public UserCreationResponse toResponse(User user) {
        final UserCreationResponse userCreationResponse =  modelMapper.map(user, UserCreationResponse.class);
        userCreationResponse.setStatus(StatusType.SUCCESSFUL);
        return userCreationResponse;
    }

}
