package com.everyhealth.backend.domain.plan.domain;

import com.everyhealth.backend.domain.exercise.domain.Exercise;
import com.everyhealth.backend.domain.user.domain.User;
import com.everyhealth.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Plan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @NotNull private LocalDate date;

    private Integer plannedTime;

    private Integer doneTime;

    @Builder
    private Plan(
            User user, Exercise exercise, LocalDate date, Integer plannedTime, Integer doneTime) {
        this.user = user;
        this.exercise = exercise;
        this.date = date;
        this.plannedTime = plannedTime;
        this.doneTime = doneTime;
    }

    public static Plan of(User user, Exercise exercise, LocalDate date, Integer plannedTime) {
        return Plan.builder()
                .user(user)
                .exercise(exercise)
                .date(date)
                .plannedTime(plannedTime)
                .doneTime(0)
                .build();
    }
}
