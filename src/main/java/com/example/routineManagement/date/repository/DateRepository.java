package com.example.routineManagement.date.repository;

import com.example.routineManagement.date.entity.Date;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateRepository extends JpaRepository<Date, Long> {
    boolean existsById(Long id);

    List<Date> findAllByAuthor(String author);
}