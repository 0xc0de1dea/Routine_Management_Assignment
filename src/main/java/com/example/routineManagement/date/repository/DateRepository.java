package com.example.routineManagement.date.repository;

import com.example.routineManagement.date.entity.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DateRepository extends JpaRepository<Date, Long> {
    boolean existsById(Long id);

    @Query("SELECT d FROM Date d WHERE d.user.name = :userName")
    List<Date> findAllByAuthor(@Param("userName") String userName);

    Page<Date> findAll(Pageable pageable);
}