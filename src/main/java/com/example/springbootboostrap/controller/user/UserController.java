package com.example.springbootboostrap.controller.user;

import com.example.springbootboostrap.annotation.LimitedTraffic;
import com.example.springbootboostrap.dto.request.user.UserCreationRequest;
import com.example.springbootboostrap.dto.response.BaseResponse;
import com.example.springbootboostrap.enums.BandwidthType;
import com.example.springbootboostrap.route.AppRoute;
import com.example.springbootboostrap.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<BaseResponse> createUser(@RequestBody UserCreationRequest request) {
        final BaseResponse response = userService.createUser(request);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(AppRoute.User.GET_ALL_USER)
    public ResponseEntity<BaseResponse> getAllUsers() {
        final BaseResponse response = userService.getAllUsers();
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @LimitedTraffic(endpointName = AppRoute.User.GET_USER_BY_ID, bandwidthType = BandwidthType.FIVE_REQUESTS_PER_MINUTE)
    @GetMapping(AppRoute.User.GET_USER_BY_ID)
    public ResponseEntity<BaseResponse> getUserById(@PathVariable Long id) {
        final BaseResponse response = userService.getUserById(id);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }
}
