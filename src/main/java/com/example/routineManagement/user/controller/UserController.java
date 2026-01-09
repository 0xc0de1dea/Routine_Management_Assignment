package com.example.routineManagement.user.controller;

import com.example.routineManagement.user.dto.LoginDto;
import com.example.routineManagement.user.dto.SignupDto;
import com.example.routineManagement.user.dto.UserDto;
import com.example.routineManagement.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @Valid @RequestBody SignupDto request
    ){
        userService.signup(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/name")
    public ResponseEntity<?> findByName(
            @Valid @RequestBody UserDto.Request request
    ){
        UserDto.Response response = userService.findByName(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<UserDto.Response> users = userService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginDto.Request request,
            HttpSession session
    ){
        LoginDto.Response response = userService.login(request);
        session.setAttribute("loginUser", response);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
