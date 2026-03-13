package com.hightech.university.modules.auth.service;

import com.hightech.university.config.JwtService;
import com.hightech.university.modules.auth.models.dto.AuthenticationRequest;
import com.hightech.university.modules.auth.models.dto.AuthenticationResponse;
import com.hightech.university.modules.auth.models.dto.RegisterRequest;
import com.hightech.university.modules.user.models.mapper.UserMapper;
import com.hightech.university.modules.user.models.dto.UserDto;
import com.hightech.university.modules.user.models.entity.User;
import com.hightech.university.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserDto register(RegisterRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(Objects.requireNonNull(passwordEncoder.encode(request.getPassword())))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        return userMapper.toDto(userRepository.save(user));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        if (!(authentication.getPrincipal() instanceof User user)) {
            throw new BadCredentialsException("Invalid Credentials");
        }
        var userDto = userMapper.toDto(user);
        var token = jwtService.generateToken(user);
        return new AuthenticationResponse(token, userDto);
    }

}
