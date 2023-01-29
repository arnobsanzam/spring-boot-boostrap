package com.example.springbootboostrap.service.user;

import com.example.springbootboostrap.constant.AppConstant;
import com.example.springbootboostrap.dto.request.user.UserCreationRequest;
import com.example.springbootboostrap.dto.response.user.UserCreationResponse;
import com.example.springbootboostrap.entity.Role;
import com.example.springbootboostrap.entity.User;
import com.example.springbootboostrap.exception.BaseException;
import com.example.springbootboostrap.mapper.UserMapper;
import com.example.springbootboostrap.record.UserInfo;
import com.example.springbootboostrap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserCreationResponse createUser(UserCreationRequest request) {
        try {
            validateUserCreationRequest(request);
            final User user = userMapper.toEntity(request);
            final Role role = new Role();
            role.setRoleName("USER");
            user.setRoles(new HashSet<>(Collections.singletonList(role)));
            final User savedUser = userRepository.save(user);
            return userMapper.toResponse(savedUser);
        } catch (Exception ex) {
            return userMapper.toErrorResponse(new UserCreationResponse(), ex);
        }
    }

    private void validateUserCreationRequest(UserCreationRequest request) {
        validateUsername(request.getUsername());
        validatePassword(request.getPassword());
    }

    private void validatePassword(String password) {
    }

    private void validateUsername(String username) {
        
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new BaseException(String.format(AppConstant.ExceptionMessage.USER_DOES_NOT_EXIST_BY_USERNAME, username)));
    }

    @Override
    public UserInfo getUserInfoByUsername(String username) {
        final User user = getUserByUsername(username);
        return new UserInfo(username, user.getAuthorities());
    }
}
