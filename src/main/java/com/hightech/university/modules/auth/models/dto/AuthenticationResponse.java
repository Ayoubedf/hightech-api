package com.hightech.university.modules.auth.models.dto;

import com.hightech.university.modules.user.models.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private String token;
    private UserDto user;
}
