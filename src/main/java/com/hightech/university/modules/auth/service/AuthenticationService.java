package com.hightech.university.modules.auth.service;

import com.hightech.university.config.JwtService;
import com.hightech.university.modules.auth.models.dto.AuthenticationResponse;
import com.hightech.university.modules.auth.models.dto.RegisterRequest;
import com.hightech.university.modules.user.models.entity.User;
import com.hightech.university.modules.user.models.mapper.UserMapper;
import com.hightech.university.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .first_name(request.getFirstName())
                .last_name(request.getLastName())
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return new AuthenticationResponse(token, userMapper.toDto(user));
    }

}
