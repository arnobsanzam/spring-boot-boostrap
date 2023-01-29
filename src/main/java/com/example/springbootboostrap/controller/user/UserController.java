package com.example.springbootboostrap.controller.user;

import com.example.springbootboostrap.dto.request.user.UserCreationRequest;
import com.example.springbootboostrap.dto.response.user.UserCreationResponse;
import com.example.springbootboostrap.dto.response.user.UserListResponse;
import com.example.springbootboostrap.route.AppRoute;
import com.example.springbootboostrap.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppRoute.API)
public class UserController {

    private final UserService userService;

    @PostMapping(AppRoute.User.CREATE_USER)
    public ResponseEntity<UserCreationResponse> createUser(@RequestBody UserCreationRequest request) {
        return ResponseEntity.ok().body(userService.createUser(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(AppRoute.User.GET_ALL_USER)
    public ResponseEntity<UserListResponse> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
}
