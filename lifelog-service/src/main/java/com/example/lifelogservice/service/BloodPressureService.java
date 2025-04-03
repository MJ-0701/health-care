package com.example.lifelogservice.service;

import com.example.lifelogservice.domain.entity.Lifelog;
import com.example.lifelogservice.domain.entity.LogType;
import com.example.lifelogservice.domain.repository.LifelogRepository;
import com.example.storagemodule.dto.request.LifelogMessageDto;
import com.example.storagemodule.dto.response.BloodPressureResponseDto;
import com.example.storagemodule.dto.response.LifelogResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
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
}
