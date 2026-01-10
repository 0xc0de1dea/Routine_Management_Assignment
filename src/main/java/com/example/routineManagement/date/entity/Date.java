package com.example.routineManagement.date.entity;

import com.example.routineManagement.comment.entity.Comment;
import com.example.routineManagement.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "dates")
@Entity
public class Date extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String content;

    private Long commentNum;

    @OneToMany(
            mappedBy = "date",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<Comment> comments;
}