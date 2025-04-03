package com.example.storagemodule.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TimelineQueryDto {
    private String ci;          // 사용자 식별을 위한 ci 값
    private String periodType;  // "7d", "1m", "12m" 등
}
