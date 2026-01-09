package com.example.routineManagement.date.controller;

import com.example.routineManagement.date.dto.DateDto;
import com.example.routineManagement.date.service.DateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/date")
public class DateController {
    private final DateService dateService;

    @PostMapping
    public ResponseEntity<?> createDate(
            @Valid @RequestBody DateDto.Request request
    ){
        return ResponseEntity.ok(dateService.createDate(request));
    }

    @GetMapping
    public ResponseEntity<?> searchDateByAuthor(
            @RequestParam(name = "author") String author
    ){
        return ResponseEntity.ok(dateService.searchDateByAuthor(author));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDate(
            @PathVariable Long id,
            @Valid @RequestBody DateDto.Request request
    ){
        return ResponseEntity.ok(dateService.updateDate(id, request));
    }

    @DeleteMapping
    public void deleteDate(
            @RequestParam(name = "id") Long id
    ){
        dateService.deleteDate(id);
    }
}