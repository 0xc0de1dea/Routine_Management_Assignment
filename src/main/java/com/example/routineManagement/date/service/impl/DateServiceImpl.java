package com.example.routineManagement.date.service.impl;

import com.example.routineManagement.comment.dto.CommentDto;
import com.example.routineManagement.comment.entity.Comment;
import com.example.routineManagement.comment.repository.CommentRepository;
import com.example.routineManagement.date.dto.DateCommentDto;
import com.example.routineManagement.date.dto.DateDto;
import com.example.routineManagement.date.entity.Date;
import com.example.routineManagement.date.repository.DateRepository;
import com.example.routineManagement.date.service.DateService;
import com.example.routineManagement.exception.CustomException;
import com.example.routineManagement.type.ErrorCode;
import com.example.routineManagement.user.dto.LoginDto;
import com.example.routineManagement.user.entity.User;
import com.example.routineManagement.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DateServiceImpl implements DateService {
    private final UserRepository userRepository;
    private final DateRepository dateRepository;
    private final CommentRepository commentRepository;

    @Override
    public DateDto.Response createDate(DateDto.Request request, HttpSession session) {
        LoginDto.Response loginDto = (LoginDto.Response) session.getAttribute("loginUser");

        User user = userRepository.findByName(loginDto.getName()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        Date date = Date.builder()
                        .user(user)
                                .content(request.getContent())
                                        .author(request.getAuthor())
                                                .build();

        date.setModifiedAt(LocalDateTime.now());
        dateRepository.save(date);

        return DateDto.Response.fromEntity(date);
    }

    @Override
    @Transactional
    public List<DateDto.Response> searchDateByAuthor(String author) {
        List<Date> dateList = dateRepository.findAllByAuthor(author);

        if (dateList.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_AUTHOR);
        }

        List<DateDto.Response> respList = new ArrayList<>();

        for (Date date : dateList) {
            respList.add(DateDto.Response.fromEntity(date));
        }

        respList.sort(new Comparator<DateDto.Response>() {
            @Override
            public int compare(DateDto.Response o1, DateDto.Response o2) {
                return o2.getModifiedAt().compareTo(o1.getModifiedAt());
            }
        });

        return respList;
    }

    @Override
    @Transactional
    public DateDto.Response searchDateById(Long id) {
        Date date = dateRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_ID));

        return DateDto.Response.fromEntity(date);
    }

    @Override
    @Transactional
    public DateDto.Response updateDate(Long id, DateDto.Request request, HttpSession session) {
        LoginDto.Response loginDto = (LoginDto.Response) session.getAttribute("loginUser");

        User user = userRepository.findByName(loginDto.getName()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        Date date = dateRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_ID));

        if (!user.getName().equals(date.getUser().getName())){
            throw new CustomException(ErrorCode.INVALID_ACCESS);
        }

        date.setAuthor(request.getAuthor());
        date.setContent(request.getContent());

        return DateDto.Response.fromEntity(dateRepository.save(date));
    }

    @Override
    @Transactional
    public void deleteDate(Long id, HttpSession session) {
        LoginDto.Response loginDto = (LoginDto.Response) session.getAttribute("loginUser");

        User user = userRepository.findByName(loginDto.getName()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        Date date = dateRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_ID));

        if (!user.getName().equals(date.getUser().getName())){
            throw new CustomException(ErrorCode.INVALID_ACCESS);
        }

        dateRepository.delete(date);
    }

    @Override
    @Transactional
    public DateCommentDto searchDateCommentById(Long id) {
        Date date = dateRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_ID));

        DateDto.Response dateDto = DateDto.Response.fromEntity(date);

        List<Comment> respList = commentRepository.findCommentByDateId(id);

        List<CommentDto.Response> respDtoList = new ArrayList<>();

        for (Comment comment : respList) {
            respDtoList.add(CommentDto.Response.fromEntity(comment));
        }

        respDtoList.sort(Comparator.comparing(CommentDto.Response::getModifiedAt));

        DateCommentDto dateCommentDto = DateCommentDto.builder()
                .date(dateDto)
                .comments(respDtoList)
                .build();

        return dateCommentDto;
    }
}
