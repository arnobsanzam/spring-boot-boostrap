package com.example.springbootboostrap.service.authentication;

import com.example.springbootboostrap.constant.AppConstant;
import com.example.springbootboostrap.dto.request.authentication.LoginRequest;
import com.example.springbootboostrap.dto.response.authentication.LoginResponse;
import com.example.springbootboostrap.entity.User;
import com.example.springbootboostrap.exception.BaseException;
import com.example.springbootboostrap.mapper.AuthenticationMapper;
import com.example.springbootboostrap.service.user.UserService;
import com.example.springbootboostrap.util.AppUtil;
import com.example.springbootboostrap.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final AuthenticationMapper authenticationMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
        final User user = userService.getUserByUsername(request.getUsername());
        if (!user.isEnabled()) {
            final long elapsedTimeInMinutes = AppUtil.getTimeDifferenceInMinutes(user.getAccountLockedAt(), new Date());
            if (elapsedTimeInMinutes < 1) {
                throw new BaseException(AppConstant.ExceptionMessage.USER_IS_TEMPORARILY_DISABLED);
            }
        }
        if (!isPasswordValid(request.getPassword(), user.getPassword())) {
            saveUserForUnsuccessfulLogin(user);
            throw new BaseException(AppConstant.ExceptionMessage.INVALID_CREDENTIALS);
        } else {
            saveUserForSuccessfulLogin(user);
        }
    }

    private boolean isPasswordValid(String requestedPassword, String password) {
        return bCryptPasswordEncoder.matches(requestedPassword, password);
    }

    private void saveUserForUnsuccessfulLogin(User user) {
        if (user.isEnabled()) {
            int failedAttemptCount = Objects.isNull(user.getFailedAttemptCount()) ? 0 : user.getFailedAttemptCount();
            user.setFailedAttemptCount(Math.min(failedAttemptCount + 1, 5));
            user.setEnabled(user.getFailedAttemptCount() < 5);
            user.setAccountLockedAt(user.getFailedAttemptCount() >= 5 ? new Date() : null);
            userService.saveUser(user);
            if (user.getFailedAttemptCount() >= 5) {
                throw new BaseException(AppConstant.ExceptionMessage.USER_IS_BLOCKED_DUE_TO_TOO_MANY_FAILED_ATTEMPTS);
            }
        } else {
            user.setEnabled(true);
            user.setAccountLockedAt(null);
            user.setFailedAttemptCount(1);
            userService.saveUser(user);
        }
    }

    private void saveUserForSuccessfulLogin(User user) {
        user.setLastAuthenticatedAt(new Date());
        user.setEnabled(true);
        user.setFailedAttemptCount(0);
        userService.saveUser(user);
    }
}
