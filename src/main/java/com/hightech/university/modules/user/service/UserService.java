package com.hightech.university.modules.user.service;

import com.hightech.university.modules.user.models.dto.UserDto;
import com.hightech.university.modules.user.models.mapper.UserMapper;
import com.hightech.university.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getUser(UUID id) {
        return userRepository.findById(id).map(userMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
