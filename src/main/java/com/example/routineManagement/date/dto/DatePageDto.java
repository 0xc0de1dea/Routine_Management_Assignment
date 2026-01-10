package com.example.routineManagement.date.dto;

import com.example.routineManagement.date.entity.Date;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatePageDto {
    private String title;
    private String content;
    private Long commentNum;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
