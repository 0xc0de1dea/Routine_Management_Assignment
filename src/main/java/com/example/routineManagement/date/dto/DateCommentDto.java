package com.example.routineManagement.date.dto;

import com.example.routineManagement.comment.dto.CommentDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateCommentDto {
    private DateDto.Response date;
    private List<CommentDto.Response> comments;
}
