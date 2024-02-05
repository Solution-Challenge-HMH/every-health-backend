package com.everyhealth.backend.domain.exercise.repository;

import com.everyhealth.backend.domain.exercise.domain.Bookmark;
import com.everyhealth.backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>{
    Optional<Bookmark> findByUserAndId(User user, Long exerciseId);
}
