package com.example.routineManagement.comment.repository;

import com.example.routineManagement.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsById(Long id);

    Optional<Comment> findById(Long id);

    @Query("SELECT c FROM Comment c WHERE c.date.id = :dateId")
    List<Comment> findCommentByDateId(@Param("dateId") Long dateId);
}