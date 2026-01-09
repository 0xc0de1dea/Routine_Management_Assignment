package com.example.routineManagement.date.dto;

import com.example.routineManagement.date.entity.Date;
import com.example.routineManagement.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

public class DateDto {
    @Getter
    public static class Request {
        @NotBlank(message = "내용은 빈칸일 수 없습니다.")
        private String content;

        @NotBlank(message = "저자는 빈칸일 수 없습니다.")
        private String author;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private String name;
        private String content;
        private String author;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public static DateDto.Response fromEntity(Date date){
            return Response.builder()
                    .name(date.getUser().getName())
                    .content(date.getContent())
                    .author(date.getAuthor())
                    .createdAt(date.getCreatedAt())
                    .modifiedAt(date.getModifiedAt())
                    .build();
        }
    }
}