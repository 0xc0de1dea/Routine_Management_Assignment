package com.example.routineManagement.date.service;

import com.example.routineManagement.date.dto.DateCommentDto;
import com.example.routineManagement.date.dto.DateDto;
import com.example.routineManagement.date.dto.DatePageDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DateService {
    DateDto.Response createDate(DateDto.Request request, HttpSession session);

    List<DateDto.Response> searchDateByAuthor(String author);

    DateDto.Response searchDateById(Long id);



    DateDto.Response updateDate(Long id, DateDto.Request request, HttpSession session);

    void deleteDate(Long id, HttpSession session);

    DateCommentDto searchDateCommentById(Long id);

    Page<DatePageDto> searchAll(int pageNo, int size);
}
