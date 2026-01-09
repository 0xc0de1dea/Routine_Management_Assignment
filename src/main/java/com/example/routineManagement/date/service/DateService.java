package com.example.routineManagement.date.service;

import com.example.routineManagement.date.dto.DateDto;

import java.util.List;

public interface DateService {
    DateDto.Response createDate(DateDto.Request request);

    List<DateDto.Response> searchDateByAuthor(String author);

    DateDto.Response searchDateById(Long id);

    DateDto.Response updateDate(Long id, DateDto.Request request);

    void deleteDate(Long id);
}
