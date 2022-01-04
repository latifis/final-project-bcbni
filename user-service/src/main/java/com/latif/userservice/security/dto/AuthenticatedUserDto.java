package com.latif.userservice.security.dto;

import com.latif.userservice.model.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticatedUserDto {
    private String name;

    private String username;

    private String password;

    private UserRole userRole;
}