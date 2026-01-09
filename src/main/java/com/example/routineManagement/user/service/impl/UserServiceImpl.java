package com.example.routineManagement.user.service.impl;

import com.example.routineManagement.config.PasswordEncoder;
import com.example.routineManagement.exception.CustomException;
import com.example.routineManagement.type.ErrorCode;
import com.example.routineManagement.user.dto.LoginDto;
import com.example.routineManagement.user.dto.SignupDto;
import com.example.routineManagement.user.dto.UserDto;
import com.example.routineManagement.user.entity.User;
import com.example.routineManagement.user.repository.UserRepository;
import com.example.routineManagement.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto.Response findByName(UserDto.Request request) {
        User user = userRepository.findByName(request.getName()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        return UserDto.Response.fromEntity(user);
    }

    @Override
    public List<UserDto.Response> findAll() {
        List<User> list = userRepository.findAll();
        List<UserDto.Response> listResp = new ArrayList<>();

        for (User user : list){
            listResp.add(UserDto.Response.fromEntity(user));
        }

        return listResp;
    }

    @Override
    @Transactional
    public void signup(SignupDto request) {
        User user = User.builder()
                        .name(request.getName())
                                .email(request.getEmail())
                                        .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
    }

    @Override
    @Transactional
    public LoginDto.Response login(LoginDto.Request request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }

        return LoginDto.Response.fromEntity(user);
    }
}
