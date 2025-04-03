package com.example.lifelogservice.service;

import com.example.common.utils.CryptoUtil;
import com.example.common.utils.TimelineRangeCalculator;
import com.example.lifelogservice.domain.entity.Lifelog;
import com.example.lifelogservice.domain.entity.LogType;
import com.example.lifelogservice.domain.repository.LifelogRepository;
import com.example.storagemodule.dto.request.LifelogMessageDto;
import com.example.storagemodule.dto.response.BloodPressureResponseDto;
import com.example.storagemodule.dto.response.BloodPressureTimelineDto;
import com.example.storagemodule.dto.response.BloodPressureTimelineResponseDto;
import com.example.storagemodule.dto.response.LifelogResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BloodPressureService {

    private final LifelogRepository lifelogRepository;

//  혈압 로그 저장 로직.
    @Transactional
    public void saveBloodPressure(LifelogMessageDto message) {
        // 필수 필드 검증
        if (message.getUserId() == null || message.getLogType() == null || message.getPayload() == null) {
            throw new IllegalArgumentException("메시지 필수 필드(userId, logType, payload) 누락");
        }

        String userId = message.getUserId();
        String ci = message.getCi();
        LogType logType = LogType.valueOf(message.getLogType());

        @SuppressWarnings("unchecked")
        Map<String, Object> payload = message.getPayload();

        // startTime 처리: Asia/Seoul 타임존 기준 LocalDateTime 변환
        Object startTimeObj = payload.get("startTime");
        if (startTimeObj == null) {
            throw new IllegalArgumentException("payload에 startTime 필드가 없습니다.");
        }
        LocalDateTime startTime;
        try {
            if (startTimeObj instanceof LocalDateTime) {
                startTime = (LocalDateTime) startTimeObj;
            } else {
                startTime = ZonedDateTime
                        .parse(startTimeObj.toString())
                        .withZoneSameInstant(ZoneId.of("Asia/Seoul"))
                        .toLocalDateTime();
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("startTime 형식이 올바르지 않습니다: " + startTimeObj, ex);
        }

        // 상태 값: user-service에서 계산한 상태(status)를 그대로 사용, 없으면 "UNKNOWN"
        String status = message.getStatus();
        if (status == null) {
            status = "UNKNOWN";
        }

        // Lifelog 엔티티 생성 및 저장
        Lifelog lifelog = Lifelog.builder()
                .userId(userId)
                .ci(ci)
                .logType(logType)
                .startTime(startTime)
                .payload(payload)
                .isActive(true)
                .status(status)
                .build();

        lifelogRepository.save(lifelog);
        log.info("✅ lifelog 저장 완료 - lifelogId: {}", lifelog.getId());
    }

    @Transactional(readOnly = true)
    public List<BloodPressureResponseDto> getUserBloodPressure(String userCi) {
        List<LifelogResponseDto> lifelogResponseDtos = lifelogRepository.getUserBloodPressure(userCi);

        return lifelogResponseDtos.stream().map(dto -> {
            @SuppressWarnings("unchecked")
            Map<String, Object> payloadMap = (Map<String, Object>) dto.getPayload();

            Double systolic = null;
            Double diastolic = null;
            if (payloadMap != null) {
                Object sys = payloadMap.get("systolic");
                Object dia = payloadMap.get("diastolic");

                // 값이 Number 타입이면 그대로 사용하고, 그렇지 않으면 문자열로 간주하여 Double로 파싱
                if (sys instanceof Number) {
                    systolic = ((Number) sys).doubleValue();
                } else if (sys != null) {
                    try {
                        systolic = Double.valueOf(sys.toString());
                    } catch (NumberFormatException e) {
                        systolic = null;
                    }
                }

                if (dia instanceof Number) {
                    diastolic = ((Number) dia).doubleValue();
                } else if (dia != null) {
                    try {
                        diastolic = Double.valueOf(dia.toString());
                    } catch (NumberFormatException e) {
                        diastolic = null;
                    }
                }
            }

            return BloodPressureResponseDto.builder()
                    .logType(dto.getLogType())
                    .isActive(dto.getIsActive())
                    .systolic(systolic)
                    .diastolic(diastolic)
                    .startTime(dto.getStartTime())
                    .createdAt(dto.getCreatedAt())
                    .updatedAt(dto.getUpdatedAt())
                    .build();
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BloodPressureTimelineResponseDto getUserBloodPressureTimeline(String ci, String periodType) {
        // 1. 조회 기간 계산 (Helper 클래스 이용 또는 직접 계산)
        LocalDate startDate;
        LocalDate endDate;
        if ("7d".equalsIgnoreCase(periodType)) {
            LocalDate maxDate = lifelogRepository.findMaxDateByCiAndLogType(ci, LogType.BLOOD_PRESSURE);
            endDate = (maxDate == null ? LocalDate.now() : maxDate);
            startDate = endDate.minusDays(6);
        } else if ("1m".equalsIgnoreCase(periodType)) {
            endDate = LocalDate.now();
            startDate = endDate.minusMonths(1).plusDays(1);
        } else if ("12m".equalsIgnoreCase(periodType)) {
            endDate = LocalDate.now();
            startDate = endDate.minusMonths(11);
        } else {
            endDate = LocalDate.now();
            startDate = endDate.minusDays(6);
        }

        // 2. 원시 로그 데이터 조회
        List<LifelogResponseDto> rawLogs = lifelogRepository.getUserBloodPressureRaw(ci, startDate, endDate);

        // 3. 각 로그를 BloodPressureLogDto로 변환
        List<BloodPressureTimelineResponseDto.BloodPressureLogDto> logDetails = rawLogs.stream()
                .map(dto -> {
                    Integer systolic = extractIntegerFromPayload(dto.getPayload(), "systolic");
                    Integer diastolic = extractIntegerFromPayload(dto.getPayload(), "diastolic");
                    return BloodPressureTimelineResponseDto.BloodPressureLogDto.builder()
                            .systolic(systolic)
                            .diastolic(diastolic)
                            .startTime(dto.getStartTime())
                            .build();
                })
                .toList();

        // 4. 날짜별로 그룹핑 (날짜는 로그의 startTime의 날짜)
        Map<LocalDate, List<BloodPressureTimelineResponseDto.BloodPressureLogDto>> grouped = logDetails.stream()
                .collect(Collectors.groupingBy(log -> log.getStartTime().toLocalDate()));

        // 5. 각 날짜별 Daily DTO 생성 (로그 리스트와 집계값 포함)
        List<BloodPressureTimelineResponseDto.DailyBloodPressureDetailDto> dailyDetails = grouped.entrySet().stream()
                .map(entry -> {
                    LocalDate date = entry.getKey();
                    // 로그 리스트 정렬
                    List<BloodPressureTimelineResponseDto.BloodPressureLogDto> sortedLogs = entry.getValue().stream()
                            .sorted(Comparator.comparing(BloodPressureTimelineResponseDto.BloodPressureLogDto::getStartTime))
                            .collect(Collectors.toList());

                    // 최소/최대 값 계산 (헬퍼 메서드 사용)
                    Integer dailyMinSystolic = getMinValue(sortedLogs.stream()
                            .map(BloodPressureTimelineResponseDto.BloodPressureLogDto::getSystolic)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()));
                    Integer dailyMaxSystolic = getMaxValue(sortedLogs.stream()
                            .map(BloodPressureTimelineResponseDto.BloodPressureLogDto::getSystolic)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()));
                    Integer dailyMinDiastolic = getMinValue(sortedLogs.stream()
                            .map(BloodPressureTimelineResponseDto.BloodPressureLogDto::getDiastolic)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()));
                    Integer dailyMaxDiastolic = getMaxValue(sortedLogs.stream()
                            .map(BloodPressureTimelineResponseDto.BloodPressureLogDto::getDiastolic)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()));

                    return BloodPressureTimelineResponseDto.DailyBloodPressureDetailDto.builder()
                            .date(date)
                            .logs(sortedLogs)
                            .minSystolic(dailyMinSystolic)
                            .maxSystolic(dailyMaxSystolic)
                            .minDiastolic(dailyMinDiastolic)
                            .maxDiastolic(dailyMaxDiastolic)
                            .build();
                })
                .sorted(Comparator.comparing(BloodPressureTimelineResponseDto.DailyBloodPressureDetailDto::getDate))
                .collect(Collectors.toList());

        // 6. 전체 기간의 날짜 리스트 (x축)
        List<LocalDate> dates = dailyDetails.stream()
                .map(BloodPressureTimelineResponseDto.DailyBloodPressureDetailDto::getDate)
                .sorted()
                .collect(Collectors.toList());

        // 7. 전체 로그에서 최소/최대 혈압 계산
        Integer overallMinSystolic = getMinValue(logDetails.stream()
                .map(BloodPressureTimelineResponseDto.BloodPressureLogDto::getSystolic)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        Integer overallMaxSystolic = getMaxValue(logDetails.stream()
                .map(BloodPressureTimelineResponseDto.BloodPressureLogDto::getSystolic)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        Integer overallMinDiastolic = getMinValue(logDetails.stream()
                .map(BloodPressureTimelineResponseDto.BloodPressureLogDto::getDiastolic)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        Integer overallMaxDiastolic = getMaxValue(logDetails.stream()
                .map(BloodPressureTimelineResponseDto.BloodPressureLogDto::getDiastolic)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        // 8. 최종 응답 DTO 생성
        return BloodPressureTimelineResponseDto.builder()
                .dates(dates)
                .overallMinSystolic(overallMinSystolic)
                .overallMaxSystolic(overallMaxSystolic)
                .overallMinDiastolic(overallMinDiastolic)
                .overallMaxDiastolic(overallMaxDiastolic)
                .dailyStats(dailyDetails)
                .build();
    }

    // 헬퍼 메서드: 리스트에서 최소값을 반환
    private Integer getMinValue(List<Integer> values) {
        return values.stream().min(Integer::compareTo).orElse(null);
    }

    // 헬퍼 메서드: 리스트에서 최대값을 반환
    private Integer getMaxValue(List<Integer> values) {
        return values.stream().max(Integer::compareTo).orElse(null);
    }

    private Integer extractIntegerFromPayload(Object payload, String key) {
        if (payload instanceof Map<?, ?>) {
            Object value = ((Map<?, ?>) payload).get(key);
            if (value instanceof String) {
                String stringValue = (String) value;
                // Base64 인코딩 여부 판단: 영문 대소문자, 숫자, '+', '/', '='로만 구성되어 있다면
                if (stringValue.matches("^[A-Za-z0-9+/=]+$")) {
                    try {
                        String decrypted = CryptoUtil.decrypt(stringValue);
                        double d = Double.parseDouble(decrypted);
                        return (int) Math.round(d);
                    } catch (Exception e) {
                        log.error("복호화 또는 정수 변환 실패 (key: {}): {}", key, stringValue, e);
                        return null;
                    }
                } else {
                    try {
                        double d = Double.parseDouble(stringValue);
                        return (int) Math.round(d);
                    } catch (Exception e) {
                        log.error("정수 변환 실패 (key: {}): {}", key, stringValue, e);
                        return null;
                    }
                }
            } else if (value instanceof Number) {
                return ((Number) value).intValue();
            }
        }
        return null;
    }

}
