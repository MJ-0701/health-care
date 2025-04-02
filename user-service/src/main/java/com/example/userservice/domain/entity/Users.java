package com.example.userservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Users extends BaseTimeEntity {

    @EmbeddedId
    private UserId id = UserId.generate(LocalDateTime.now());

    @Column(name = "user_name", length = 100, nullable = false)
    private String userName;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", length = 1)
    private String gender; // M / F 등 (enum으로 만들고 싶으면 말해줘!)

    @Column(name = "ci", length = 255)
    private String ci; // 본인인증 후 업데이트

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;
}
