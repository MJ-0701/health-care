package com.example.common.utils;

import java.time.LocalDate;

public class TimelineRangeCalculator {

    public static LocalDateRange calculate(String periodType) {
        LocalDate now = LocalDate.now();
        return switch (periodType) {
            case "7D" -> new LocalDateRange(now.minusDays(6), now);
            case "1M" -> new LocalDateRange(now.minusMonths(1).plusDays(1), now);
            case "12M" -> new LocalDateRange(now.minusMonths(12).plusDays(1), now);
            default -> throw new IllegalArgumentException("지원하지 않는 기간 형식입니다: " + periodType);
        };
    }

    public record LocalDateRange(LocalDate start, LocalDate end) {}
}
