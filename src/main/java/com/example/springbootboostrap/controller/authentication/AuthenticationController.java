package com.example.springbootboostrap.controller.authentication;

import com.example.springbootboostrap.dto.request.authentication.LoginRequest;
import com.example.springbootboostrap.dto.response.BaseResponse;
import com.example.springbootboostrap.route.AppRoute;
import com.example.springbootboostrap.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(AppRoute.API)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(AppRoute.Auth.LOGIN)
    public ResponseEntity<BaseResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(authenticationService.login(request));
    }
}
