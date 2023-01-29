package com.example.springbootboostrap.configuration.security;

import com.example.springbootboostrap.constant.AppConstant;
import com.example.springbootboostrap.entity.User;
import com.example.springbootboostrap.exception.BaseException;
import com.example.springbootboostrap.service.user.UserService;
import com.example.springbootboostrap.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String token = getToken(request);
            JwtUtil.validateToken(token);
            final String username = JwtUtil.getUsernameFromToken(token);
            final User user = userService.getUserByUsername(username);
            final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (Exception ex) {

        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        final String requestTokenHeader = request.getHeader("Authorization");
        if (Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
            return requestTokenHeader.substring(7);
        }

        throw new BaseException(AppConstant.ExceptionMessage.BEARER_TOKEN_NOT_FOUND);
    }
}
