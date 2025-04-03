package com.example.storagemodule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LifelogMessageDto {
    private String userId;
    private String ci;
    private String logType;
    private String status;
    private Map<String, Object> payload;

}