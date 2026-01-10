package com.example.routineManagement.comment.service;

import com.example.routineManagement.comment.dto.CommentDto;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface CommentService {
    CommentDto.Response createComment(Long id, CommentDto.Request request, HttpSession session);

    List<CommentDto.Response> searchCommentById(Long id);

    CommentDto.Response updateComment(Long id, CommentDto.Request request, HttpSession session);

    void deleteComment(Long id, HttpSession session);
}