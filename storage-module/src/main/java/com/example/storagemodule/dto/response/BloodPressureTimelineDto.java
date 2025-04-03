package com.example.storagemodule.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Builder
public class BloodPressureTimelineDto {

    private LocalDate date;
    private Integer minSystolic;
    private Integer maxSystolic;
    private Integer minDiastolic;
    private Integer maxDiastolic;

    @QueryProjection
    public BloodPressureTimelineDto(LocalDate date,
                                    Integer minSystolic,
                                    Integer maxSystolic,
                                    Integer minDiastolic,
                                    Integer maxDiastolic) {
        this.date = date;
        this.minSystolic = minSystolic;
        this.maxSystolic = maxSystolic;
        this.minDiastolic = minDiastolic;
        this.maxDiastolic = maxDiastolic;
    }
}
