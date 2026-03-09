package com.hightech.university.modules.user.models.dto;

import com.hightech.university.modules.user.models.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}
