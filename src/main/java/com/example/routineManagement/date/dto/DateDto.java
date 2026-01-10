package com.example.routineManagement.date.dto;

import com.example.routineManagement.date.entity.Date;
import com.example.routineManagement.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

public class DateDto {
    @Getter
    public static class Request {
        @NotBlank(message = "제목은 빈칸일 수 없습니다.")
        @Size(max = 20, message = "제목은 최대 20글자 입니다.")
        private String title;

        @NotBlank(message = "내용은 빈칸일 수 없습니다.")
        @Size(max = 100, message = "내용은 최대 100글자 입니다.")
        private String content;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private String title;
        private String content;
        private String author;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public static DateDto.Response fromEntity(Date date){
            return Response.builder()
                    .title(date.getTitle())
                    .content(date.getContent())
                    .author(date.getUser().getName())
                    .createdAt(date.getCreatedAt())
                    .modifiedAt(date.getModifiedAt())
                    .build();
        }
    }
}