package com.example.userservice.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserBloodPressureResponseDto {
    private String userName;
    private String gender;
    private String logType;
    private Boolean isActive;
    private Double systolic;
    private Double diastolic;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime startTime;
}
