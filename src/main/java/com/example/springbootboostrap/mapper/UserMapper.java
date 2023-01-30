package com.example.springbootboostrap.mapper;

import com.example.springbootboostrap.appenum.StatusType;
import com.example.springbootboostrap.dto.request.user.UserCreationRequest;
import com.example.springbootboostrap.dto.response.user.UserDetailsResponse;
import com.example.springbootboostrap.dto.response.user.UserListResponse;
import com.example.springbootboostrap.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper extends BaseMapper {

    private final ModelMapper modelMapper;

    public User toEntity(UserCreationRequest request) {
        return modelMapper.map(request, User.class);
    }

    public UserDetailsResponse toUserDetailsResponse(User user, HttpStatus httpStatus) {
        final UserDetailsResponse response =  modelMapper.map(user, UserDetailsResponse.class);
        response.setHttpStatus(httpStatus);
        response.setRequestStatus(StatusType.SUCCESSFUL);
        return response;
    }

    public UserListResponse toUserListResponse(List<User> users, HttpStatus httpStatus) {
        List<UserDetailsResponse> userResponses = modelMapper.map(users, new TypeToken<List<UserDetailsResponse>>(){}.getType());
        UserListResponse response = new UserListResponse();
        response.setHttpStatus(httpStatus);
        response.setUsers(userResponses);
        return response;
    }

}
