package com.example.userservice.web.dto;

import lombok.*;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LifelogMessageDto {
    private String ci;
    private String logType; // "BLOOD_PRESSURE"
    private Map<String, Object> payload;
}
