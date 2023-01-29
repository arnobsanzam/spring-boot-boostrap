package com.example.springbootboostrap.service.authentication;

import com.example.springbootboostrap.dto.request.authentication.LoginRequest;
import com.example.springbootboostrap.dto.response.authentication.LoginResponse;
import com.example.springbootboostrap.entity.User;
import com.example.springbootboostrap.mapper.AuthenticationMapper;
import com.example.springbootboostrap.service.user.UserService;
import com.example.springbootboostrap.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    private final AuthenticationMapper authenticationMapper;

    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            validateLoginRequest(request);
            final String token = getToken(request);
            return authenticationMapper.toLoginResponse(token);
        } catch (Exception ex) {
            return authenticationMapper.toErrorResponse(new LoginResponse(), ex);
        }
    }

    private String getToken(LoginRequest request) {
        final String username = request.getUsername();
        final User user = userService.getUserByUsername(username);
        return JwtUtil.createToken(username, user.getRoles());
    }

    private void validateLoginRequest(LoginRequest request) {
    }
}
