package com.example.userservice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

@Getter
@Embeddable
public class UserId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final String PREFIX = "UID";

    @Column(name = "user_id")
    private String userId;

    protected UserId() {
        // JPA 기본 생성자
    }

    public UserId(String userId) {
        this.userId = userId;
    }

    public static UserId generate(LocalDateTime dateTime) {
        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase(Locale.getDefault());
        return new UserId(PREFIX + formatDate(dateTime) + uuid);
    }

    private static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId that)) return false;
        return userId != null && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }
}
