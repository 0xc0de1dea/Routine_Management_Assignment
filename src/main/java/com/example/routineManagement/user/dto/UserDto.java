package com.example.routineManagement.user.dto;

import com.example.routineManagement.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class UserDto {
    @Getter
    public static class Request {
        @NotBlank(message = "이름은 빈칸일 수 없습니다.")
        private String name;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private String email;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public static UserDto.Response fromEntity(User user){
            return Response.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .createdAt(user.getCreatedAt())
                    .modifiedAt(user.getModifiedAt())
                    .build();
        }
    }
}
