package com.example.routineManagement.date.service.impl;

import com.example.routineManagement.date.dto.DateDto;
import com.example.routineManagement.date.repository.DateRepository;
import com.example.routineManagement.date.service.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DateServiceImpl implements DateService {
    private final DateRepository dateRepository;

    @Override
    public DateDto.Response createDate(DateDto.Request request) {
        return null;
    }

    @Override
    public List<DateDto.Response> searchDateByAuthor(String author) {
        return List.of();
    }

    @Override
    public DateDto.Response searchDateById(Long id) {
        return null;
    }

    @Override
    public DateDto.Response updateDate(Long id, DateDto.Request request) {
        return null;
    }

    @Override
    public void deleteDate(Long id) {

    }
}
