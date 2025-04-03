package com.example.userservice.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BloodPressureRequestDto {

    @NotNull(message = "startTime은 필수입니다.")
    private String startTime;

    @NotNull(message = "systolic은 필수입니다.")
    private Double systolic;

    @NotNull(message = "diastolic은 필수입니다.")
    private Double diastolic;
}
