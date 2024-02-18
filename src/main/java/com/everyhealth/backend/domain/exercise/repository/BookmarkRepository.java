package com.everyhealth.backend.domain.exercise.repository;

import com.everyhealth.backend.domain.exercise.domain.Bookmark;
import com.everyhealth.backend.domain.exercise.domain.Exercise;
import com.everyhealth.backend.domain.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByUserAndExercise(User user, Exercise exercise);

    boolean existsByUserAndExercise(User user, Exercise exercise);
}
