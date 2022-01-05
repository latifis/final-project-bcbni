package com.latif.userservice.service;

import com.latif.userservice.model.User;
import com.latif.userservice.payload.TokenResponse;
import com.latif.userservice.payload.UserRegister;
import com.latif.userservice.payload.UsernamePassword;

public interface AuthService {
    User register (UserRegister req);
    TokenResponse generateToken(UsernamePassword req);
}
