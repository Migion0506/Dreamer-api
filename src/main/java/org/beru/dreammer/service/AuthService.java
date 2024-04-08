package org.beru.dreammer.service;

import org.beru.dreammer.exception.RestRequestEntityExceptionHandler;
import org.beru.dreammer.persistence.entity.UserEntity;
import org.beru.dreammer.service.dto.LoginDto;
import org.beru.dreammer.web.config.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager manager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public String login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                loginDto.getPassword());
        Authentication authentication = this.manager.authenticate(login);

        if (!authentication.isAuthenticated()) {
            throw new RestRequestEntityExceptionHandler("User failed in authentication. Please try again");
        }

        String jwt = this.jwtUtil.create(loginDto.getUsername());
        return jwt;
    }

    public UserEntity account() {
        return userService.getAccount();
    }
    public boolean isValid(String token) {
        return jwtUtil.isValid(token);
    }
}
