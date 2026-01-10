package com.example.routineManagement.comment.dto;

import com.example.routineManagement.comment.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

public class CommentDto {
    @Getter
    public static class Request {
        @NotBlank
        @Size(max = 100, message = "댓글은 100자 이하만 달 수 있습니다.")
        private String content;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {
        private Long id;
        private String content;
        private String author;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public static CommentDto.Response fromEntity(Comment comment){
            return Response.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .author(comment.getUser().getName())
                    .createdAt(comment.getCreatedAt())
                    .modifiedAt(comment.getModifiedAt())
                    .build();
        }
    }
}