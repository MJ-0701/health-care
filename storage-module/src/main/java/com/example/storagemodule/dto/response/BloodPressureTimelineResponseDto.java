package com.example.storagemodule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BloodPressureTimelineResponseDto {
    // 전체 기간의 x축: 혈압 데이터가 존재하는 날짜 목록
    private List<LocalDate> dates;
    // 전체 기간의 최소/최대 수축기 혈압 값
    private Integer overallMinSystolic;
    private Integer overallMaxSystolic;
    // 전체 기간의 최소/최대 이완기 혈압 값
    private Integer overallMinDiastolic;
    private Integer overallMaxDiastolic;
    // 날짜별 상세 로그 리스트 (하루의 집계값도 포함)
    private List<DailyBloodPressureDetailDto> dailyStats;

    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class DailyBloodPressureDetailDto {
        private LocalDate date;
        // 해당 날짜의 혈압 로그 목록
        private List<BloodPressureLogDto> logs;
        // 해당 날짜의 집계값
        private Integer minSystolic;
        private Integer maxSystolic;
        private Integer minDiastolic;
        private Integer maxDiastolic;
    }

    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class BloodPressureLogDto {
        private Integer systolic;
        private Integer diastolic;
        private LocalDateTime startTime;
    }
}