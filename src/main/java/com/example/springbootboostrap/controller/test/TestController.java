package com.example.springbootboostrap.controller.test;

import com.example.springbootboostrap.dto.request.user.UserCreationRequest;
import com.example.springbootboostrap.dto.response.user.UserDetailsResponse;
import com.example.springbootboostrap.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/test")
public class TestController {

    private final UserService userService;

    @PostMapping(value = "/endpoint")
    public ResponseEntity<UserDetailsResponse> test(@RequestBody UserCreationRequest userCreationRequest) {
        return ResponseEntity.ok().body(userService.createUser(userCreationRequest));
    }
}
