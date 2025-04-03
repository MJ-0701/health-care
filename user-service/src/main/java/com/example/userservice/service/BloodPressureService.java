package com.example.userservice.service;

import com.example.storagemodule.dto.request.LifelogMessageDto;
import com.example.storagemodule.dto.response.BloodPressureResponseDto;
import com.example.storagemodule.dto.response.BloodPressureTimelineDto;
import com.example.storagemodule.dto.response.BloodPressureTimelineResponseDto;
import com.example.userservice.domain.entity.UserId;
import com.example.userservice.domain.entity.Users;
import com.example.userservice.domain.repository.UsersRepository;
import com.example.userservice.messaging.LifelogProducerService;
import com.example.userservice.security.PayloadEncryptor;
import com.example.userservice.web.dto.request.BloodPressureRequestDto;
import com.example.userservice.web.dto.response.UserBloodPressureResponseDto;
import com.example.userservice.web.dto.response.UserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BloodPressureService {

    private final LifelogProducerService lifelogProducerService;
    private final PayloadEncryptor payloadEncryptor;
    private final UsersRepository usersRepository;

    /**
        * 모든 필드가 동적으로 들어오는 JSON 데이터를 Map<String, Object>로 받고,
        * 필요한 경우 혈압값을 추출하여 상태를 계산한 후, 암호화된 payload를 메시지에 담아 전송
     */
    public void save(String userId, Map<String, Object> requestData) {
        // 1. 입력 받은 전체 데이터를 payload로 사용 (고정 필드 없이 동적으로 처리)
        Map<String, Object> payload = new HashMap<>(requestData);

        // 2. 혈압 필드가 존재하면 상태 계산
        String status = "UNKNOWN"; // 기본 상태

        Double systolic = parseToDouble(requestData.get("systolic"));
        Double diastolic = parseToDouble(requestData.get("diastolic"));
        if (systolic != null && diastolic != null) {
            status = computeStatus(systolic, diastolic);
        }

        // 3. startTime 필드가 없으면 현재 시간을 넣어줍니다.
        if (!payload.containsKey("startTime")) {
            payload.put("startTime", LocalDateTime.now());
        } else {
            // startTime이 문자열인 경우 LocalDateTime으로 변환 (형식이 올바르지 않으면 그대로 둠)
            Object startTimeObj = payload.get("startTime");
            if (startTimeObj instanceof String) {
                try {
                    LocalDateTime parsedTime = LocalDateTime.parse((String) startTimeObj);
                    payload.put("startTime", parsedTime);
                } catch (Exception e) {
                    log.warn("startTime 파싱 실패, 원래 값 유지: {}", startTimeObj);
                }
            }
        }

        // 4. 민감 데이터 암호화 (payload 전체 암호화)
        Map<String, Object> encryptedPayload = payloadEncryptor.encryptBloodPressurePayload(payload);

        // 5. 메시지 생성 및 전송 (상태는 평문으로 포함)
        LifelogMessageDto message = LifelogMessageDto.builder()
                .userId(userId)
                .ci("ci_test") // 실제 CI 값으로 대체 필요
                .logType("BLOOD_PRESSURE")
                .payload(encryptedPayload)
                .status(status)
                .build();

        lifelogProducerService.sendLifelogMessage(message);
        log.info("BloodPressureService.save 처리 완료 - userId: {}", userId);
    }

    /**
     * 입력값을 Double로 파싱하는 헬퍼 메서드.
     */
    private Double parseToDouble(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        try {
            return Double.parseDouble(value.toString());
        } catch (NumberFormatException e) {
            log.error("숫자 변환 실패: {}", value, e);
            return null;
        }
    }


    @Transactional(readOnly = true)
    public UserBloodPressureResponseDto getUserBloodPressure(String userId) {
        // 사용자 정보 조회
        Users user = usersRepository.findById_UserId(userId);
        UserInfoResponseDto userDto = UserInfoResponseDto.builder()
                .userName(user.getUserName())
                .gender(user.getGender())
                .ci(user.getCi())
                .build();

        // lifelog-service에서 ci를 기반으로 혈압 로그 데이터 조회 (RabbitMQ 동기 응답)
        List<BloodPressureResponseDto> bloodPressureLogs = getBloodPressureLog(userDto.getCi());

        List<UserBloodPressureResponseDto.UserBloodPressureLog> bpLogList = bloodPressureLogs.stream()
                .map(bp -> UserBloodPressureResponseDto.UserBloodPressureLog.builder()
                        .systolic(bp.getSystolic())
                        .diastolic(bp.getDiastolic())
                        .createdAt(bp.getCreatedAt())
                        .updatedAt(bp.getUpdatedAt())
                        .startTime(bp.getStartTime())
                        .build())
                .collect(Collectors.toList());

        // 모든 혈압 로그에 동일한 logType, isActive 값을 사용 (로그가 없으면 null 처리)
        String logType = bloodPressureLogs.isEmpty() ? null : bloodPressureLogs.get(0).getLogType();
        Boolean isActive = bloodPressureLogs.isEmpty() ? null : bloodPressureLogs.get(0).getIsActive();

        return UserBloodPressureResponseDto.builder()
                .userName(userDto.getUserName())
                .gender(userDto.getGender())
                .logType(logType)
                .isActive(isActive)
                .bloodPressure(bpLogList)
                .build();
    }


    private List<BloodPressureResponseDto> getBloodPressureLog(String ci) {
        Object response = lifelogProducerService.getUserBloodPressureLog(ci, "BLOOD_PRESSURE");
        if (response instanceof List<?>) {
            return ((List<?>) response).stream()
                    .filter(BloodPressureResponseDto.class::isInstance)
                    .map(BloodPressureResponseDto.class::cast)
                    .collect(Collectors.toList());
        }
        throw new IllegalStateException("예상하지 못한 응답 타입: " + response.getClass());
    }

    @Transactional(readOnly = true)
    public BloodPressureTimelineResponseDto getUserBloodPressureTimeline(String userId, String periodType) {
        // 1. 사용자 정보 조회 후 ci 획득
        Users user = usersRepository.findById_UserId(userId);
        if (user == null) {
            throw new IllegalArgumentException("사용자 정보가 존재하지 않습니다: " + userId);
        }
        String ci = user.getCi();
        log.info("사용자 {}의 ci 값: {}", userId, ci);

        // 2. lifelogProducerService를 이용해 RPC 요청 전송 후 응답 수신
        BloodPressureTimelineResponseDto response = lifelogProducerService.getUserBloodPressureTimeline(ci, periodType);
        log.info("타임라인 조회 응답: {}", response);

        if (response != null) {
            return response;
        } else {
            throw new RuntimeException("타임라인 응답 타입이 올바르지 않습니다. 실제 응답 타입: null");
        }
    }



    /**
     * 혈압 상태를 계산하는 메서드.
     * 수축기와 이완기 혈압을 모두 고려하여, 저혈압, 정상, 고혈압을 구분합니다.
     *
     * 기준 예시:
     * - 저혈압: 수축기 < 90 또는 이완기 < 60
     * - 정상: 수축기 90~120, 이완기 60~80 범위 내
     * - 고혈압: 수축기 > 120 또는 이완기 > 80
     *
     * @param systolic  수축기 혈압 값
     * @param diastolic 이완기 혈압 값
     * @return 혈압 상태 (LOW_BP, NORMAL_BP, HIGH_BP, 또는 UNKNOWN)
     */
    private String computeStatus(Double systolic, Double diastolic) {
        if (systolic == null || diastolic == null) {
            return "UNKNOWN";
        }

        // 저혈압: 수축기 혹은 이완기 중 하나라도 기준 이하인 경우
        if (systolic < 90 || diastolic < 60) {
            return "LOW_BP";
        }

        // 정상 혈압: 수축기와 이완기가 모두 정상 범위 내에 있는 경우
        if (systolic >= 90 && systolic <= 120 && diastolic >= 60 && diastolic <= 80) {
            return "NORMAL_BP";
        }

        // 고혈압: 정상 범위를 초과하는 경우
        if (systolic > 120 || diastolic > 80) {
            return "HIGH_BP";
        }

        return "UNKNOWN";
    }
}
