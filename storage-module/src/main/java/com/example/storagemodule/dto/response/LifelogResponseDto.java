package com.example.storagemodule.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
public class LifelogResponseDto {
    private String logType;
    private Boolean isActive;
    private Object payload;
    private LocalDateTime startTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @QueryProjection
    public LifelogResponseDto(String logType, Boolean isActive, Object payload,
                              LocalDateTime startTime, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.logType = logType;
        this.isActive = isActive;
        this.payload = payload;
        this.startTime = startTime;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }
}
