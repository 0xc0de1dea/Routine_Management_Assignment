package com.example.routineManagement.user.service;

import com.example.routineManagement.user.dto.LoginDto;
import com.example.routineManagement.user.dto.SignupDto;
import com.example.routineManagement.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto.Response findByName(UserDto.Request request);

    List<UserDto.Response> findAll();

    void signup(SignupDto request);

    LoginDto.Response login(LoginDto.Request request);
}
