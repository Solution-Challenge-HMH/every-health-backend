package com.everyhealth.backend.domain.user.domain;


import com.everyhealth.backend.global.entity.BaseEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "USER")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(unique = true)
    private String email;

    @Nullable
    @Size(max = 2)
    private String gender;

    @Nullable
    @Size(max = 30)
    private String nickname;


    // 생성자
    @Builder
    private User(
            String email,
            String gender,
            String nickname) {
        this.email = email;
        this.gender = gender;
        this.nickname = nickname;
    }

    public static User of() {
        return User.builder()
                .build();
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}