package com.example.lifelogservice.web.dto;

import lombok.*;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LifelogMessageDto {

    private String userId;
    private String ci;
    private String logType;
    private Map<String, Object> payload;

}
