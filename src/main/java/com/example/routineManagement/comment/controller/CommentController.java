package com.example.routineManagement.comment.controller;

import com.example.routineManagement.comment.dto.CommentDto;
import com.example.routineManagement.comment.service.CommentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/date/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{id}")
    public ResponseEntity<?> createComment(
            @PathVariable Long id,
            @Valid @RequestBody CommentDto.Request request,
            HttpSession session
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(id, request, session));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchCommentById(
            @PathVariable Long id
    ){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.searchCommentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(
            @PathVariable Long id,
            @Valid @RequestBody CommentDto.Request request,
            HttpSession session
    ){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(id, request, session));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long id,
            HttpSession session
    ){
        commentService.deleteComment(id, session);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}