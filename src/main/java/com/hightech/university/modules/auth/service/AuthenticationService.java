package com.hightech.university.modules.auth.service;

import com.hightech.university.modules.auth.models.dto.RegisterRequest;
import com.hightech.university.modules.user.models.mapper.UserMapper;
import com.hightech.university.modules.user.models.dto.UserDto;
import com.hightech.university.modules.user.models.entity.User;
import com.hightech.university.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(Objects.requireNonNull(passwordEncoder.encode(request.getPassword())))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        return userMapper.toDto(userRepository.save(user));
    }

}
