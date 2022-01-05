package com.latif.userservice.controller;

import com.latif.userservice.payload.BaseResponse;
import com.latif.userservice.payload.TokenResponse;
import com.latif.userservice.payload.UserInfo;
import com.latif.userservice.payload.UserRegister;
import com.latif.userservice.payload.UsernamePassword;
import com.latif.userservice.service.AuthService;
import com.latif.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse<UserRegister>> register(@RequestBody UserRegister userRegister){
        if (userRegister.getUsername() == null){
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Bad Request"), HttpStatus.BAD_REQUEST);
        }
        try {
            authService.register(userRegister); //ini
            return ResponseEntity.ok(new BaseResponse<>(userRegister));
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Duplicated")){
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                        "Duplicated"), HttpStatus.CONFLICT);
            }
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<?>> getToken(@RequestBody UsernamePassword usernamePassword){
        if (usernamePassword.getUsername() == null){
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Bad Request"), HttpStatus.BAD_REQUEST);
        }
        try {
            TokenResponse token = authService.generateToken(usernamePassword); // ini
            return ResponseEntity.ok(new BaseResponse<>(token));
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Bad Credential")){
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                        "Bad Credential"), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/info")
    public ResponseEntity<BaseResponse<?>> getUserInfo(Principal principal){
        if (principal.getName() == null){
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Bad Request"), HttpStatus.BAD_REQUEST);
        }
        try {
            UserInfo userInfo = userService.getUserInfo(principal.getName());
            return ResponseEntity.ok(new BaseResponse<>(userInfo));
        }catch (Exception e){
            if(e.getMessage().equalsIgnoreCase("Bad Credential")){
                return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                        "Bad Credential"), HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity(new BaseResponse(Boolean.FALSE,
                    "Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
