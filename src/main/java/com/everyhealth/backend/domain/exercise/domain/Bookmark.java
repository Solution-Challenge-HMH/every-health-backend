package com.everyhealth.backend.domain.exercise.domain;

import com.everyhealth.backend.domain.user.domain.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "BOOKMARK")
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Cascade
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Builder
    private Bookmark(User user, Exercise exercise) {
        this.user = user;
        this.exercise = exercise;
    }

    public static Bookmark of(User user, Exercise exercise) {
        return Bookmark.builder().user(user).exercise(exercise).build();
    }
}
