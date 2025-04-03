package com.example.userservice.web.dto.request;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

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

    // 추가 속성을 담을 맵 (unknown properties)
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        additionalProperties.put(name, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }
}
