package com.example.routineManagement.user.dto;

import com.example.routineManagement.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class LoginDto {
    @Getter
    public static class Request {
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "비밀번호는 빈칸일 수 없습니다.")
        private String password;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private String email;

        public static LoginDto.Response fromEntity(User user){
            return LoginDto.Response.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        }
    }
}
