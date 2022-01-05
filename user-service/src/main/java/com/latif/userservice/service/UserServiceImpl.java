package com.latif.userservice.service;

import com.latif.userservice.model.User;
import com.latif.userservice.payload.UserInfo;
import com.latif.userservice.repository.UserRepository;
import com.latif.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getDistinctTopByUsername(username);
        if (user==null)
            throw new UsernameNotFoundException("Username Not Found");

        return user;
    }

    @Override
    public UserInfo getUserInfo(String username) throws UsernameNotFoundException {
        User user = userRepository.getDistinctTopByUsername(username);
        if (user==null)
            throw new UsernameNotFoundException("Username Not Found");
        UserInfo userInfo =  new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setUsername(username);
        userInfo.setEmail(user.getEmail());
        userInfo.setRole(user.getRole());
        userInfo.set_registered(user.is_registered());
        return userInfo;
    }

    @Override
    public List<UserInfo> getAllUserInfo(String username) {
        if (getUserInfo(username).role.equalsIgnoreCase("admin")){
            Iterable<User> users = userRepository.findAll();
            List<UserInfo> usersInfo = new ArrayList<>();
            for (User user : users){
                UserInfo userInfo = UserInfo.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .is_registered(user.is_registered())
                        .build();
                usersInfo.add(userInfo);
            }
            return usersInfo;
        }else {
            throw new RuntimeException("Unauthorized");
        }

    }

}
