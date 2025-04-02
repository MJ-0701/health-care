package com.example.lifelogservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "lifelog")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Lifelog extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "user_id", nullable = false, length = 100)
    private String userId;

    @Column(nullable = false, length = 255)
    private String ci;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "log_type", nullable = false, length = 50)
    private LogType logType;

    @Convert(converter = JsonPayloadConverter.class)
    @Column(columnDefinition = "jsonb", nullable = false)
    private Object payload;

    @Column(length = 20)
    private String status = "NORMAL";

    @Column(name = "start_time", nullable = false)
    private ZonedDateTime startTime;
}
