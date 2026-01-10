package com.example.routineManagement.comment.service.impl;

import com.example.routineManagement.comment.dto.CommentDto;
import com.example.routineManagement.comment.entity.Comment;
import com.example.routineManagement.comment.repository.CommentRepository;
import com.example.routineManagement.comment.service.CommentService;
import com.example.routineManagement.date.entity.Date;
import com.example.routineManagement.date.repository.DateRepository;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final DateRepository dateRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public CommentDto.Response createComment(Long id, CommentDto.Request request, HttpSession session) {
        LoginDto.Response loginDto = (LoginDto.Response) session.getAttribute("loginUser");

        User user = userRepository.findByName(loginDto.getName()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        Date date = dateRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_ID));

        Comment comment = Comment.builder()
                        .content(request.getContent())
                                .user(user)
                                        .date(date)
                                                .build();

        date.setCommentNum(date.getCommentNum() + 1);
        dateRepository.save(date);
        comment.setModifiedAt(LocalDateTime.now());
        commentRepository.save(comment);

        return CommentDto.Response.fromEntity(comment);
    }

    @Override
    public List<CommentDto.Response> searchCommentById(Long id) {
        Date date = dateRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_ID));

        List<Comment> list = commentRepository.findCommentByDateId(id);
        List<CommentDto.Response> respList = new ArrayList<>();

        for (Comment comment : list) {
            respList.add(CommentDto.Response.fromEntity(comment));
        }

        return respList;
    }

    @Override
    public CommentDto.Response updateComment(Long id, CommentDto.Request request, HttpSession session) {
        LoginDto.Response loginDto = (LoginDto.Response) session.getAttribute("loginUser");

        User user = userRepository.findByName(loginDto.getName()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_ID)
        );

        if (!comment.getUser().getName().equals(user.getName())){
            throw new CustomException(ErrorCode.INVALID_ACCESS);
        }

        comment.setContent(request.getContent());
        commentRepository.save(comment);

        return CommentDto.Response.fromEntity(comment);
    }

    @Override
    public void deleteComment(Long id, HttpSession session) {
        LoginDto.Response loginDto = (LoginDto.Response) session.getAttribute("loginUser");

        User user = userRepository.findByName(loginDto.getName()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_ID)
        );

        if (!comment.getUser().getName().equals(user.getName())){
            throw new CustomException(ErrorCode.INVALID_ACCESS);
        }

        Date date = comment.getDate();
        date.setCommentNum(date.getCommentNum() - 1);

        dateRepository.save(date);
        commentRepository.delete(comment);
    }
}