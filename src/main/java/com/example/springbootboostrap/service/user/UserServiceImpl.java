package com.example.springbootboostrap.service.user;

import com.example.springbootboostrap.enums.RoleType;
import com.example.springbootboostrap.constant.AppConstant;
import com.example.springbootboostrap.dto.request.user.UserCreationRequest;
import com.example.springbootboostrap.dto.response.user.UserDetailsResponse;
import com.example.springbootboostrap.dto.response.user.UserListResponse;
import com.example.springbootboostrap.entity.Role;
import com.example.springbootboostrap.entity.User;
import com.example.springbootboostrap.exception.BaseException;
import com.example.springbootboostrap.exception.EntityNotFoundException;
import com.example.springbootboostrap.mapper.UserMapper;
import com.example.springbootboostrap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetailsResponse createUser(UserCreationRequest request) {
        validateUserCreationRequest(request);
        final User user = userMapper.toEntity(request);
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        final Role role = new Role();
        role.setRoleName(RoleType.ROLE_USER);
        user.setRoles(new HashSet<>(Collections.singletonList(role)));
        final User savedUser = userRepository.save(user);
        return userMapper.toUserDetailsResponse(savedUser, HttpStatus.CREATED);
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new BaseException(String.format(AppConstant.ExceptionMessage.USER_DOES_NOT_EXIST_BY_USERNAME, username)));
    }

    @Override
    public UserListResponse getAllUsers() {

        List<User> users = userRepository.findAll();
        return userMapper.toUserListResponse(users, HttpStatus.OK);

    }

    @Override
    public UserDetailsResponse getUserById(Long id) {
        final User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(AppConstant.ExceptionMessage.USER_DOES_NOT_EXIST_BY_ID));
        return userMapper.toUserDetailsResponse(user, HttpStatus.OK);
    }

    private void validateUserCreationRequest(UserCreationRequest request) {
        validateUsername(request.getUsername());
        validatePassword(request.getPassword());
    }

    private void validatePassword(String password) {
    }

    private void validateUsername(String username) {

    }
}
