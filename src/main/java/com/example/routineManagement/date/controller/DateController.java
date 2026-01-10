package com.example.routineManagement.date.controller;

import com.example.routineManagement.date.dto.DateDto;
import com.example.routineManagement.date.service.DateService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/date")
public class DateController {
    private final DateService dateService;

    @PostMapping
    public ResponseEntity<?> createDate(
            @Valid @RequestBody DateDto.Request request,
            HttpSession session
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(dateService.createDate(request, session));
    }

    @GetMapping
    public ResponseEntity<?> searchDateByAuthor(
            @RequestParam(name = "author") String author
    ){
        return ResponseEntity.ok(dateService.searchDateByAuthor(author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchDateCommentById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(dateService.searchDateCommentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDate(
            @PathVariable Long id,
            @Valid @RequestBody DateDto.Request request,
            HttpSession session
    ){
        return ResponseEntity.ok(dateService.updateDate(id, request, session));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDate(
            @RequestParam(name = "id") Long id,
            HttpSession session
    ){
        dateService.deleteDate(id, session);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}