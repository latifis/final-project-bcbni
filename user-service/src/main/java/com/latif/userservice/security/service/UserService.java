package com.latif.userservice.security.service;

import com.latif.userservice.model.User;
import com.latif.userservice.security.dto.AuthenticatedUserDto;
import com.latif.userservice.security.dto.RegistrationRequest;
import com.latif.userservice.security.dto.RegistrationResponse;

public interface UserService {
    User findByUsername(String username);

    RegistrationResponse registration(RegistrationRequest registrationRequest);

    AuthenticatedUserDto findAuthenticatedUserByUsername(String username);
}
