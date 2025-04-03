package com.example.userservice.service;

import com.example.storagemodule.dto.request.LifelogMessageDto;
import com.example.storagemodule.dto.response.BloodPressureResponseDto;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BloodPressureService {

    private final LifelogProducerService lifelogProducerService;
    private final PayloadEncryptor payloadEncryptor;
    private final UsersRepository usersRepository;

    public void save(String userId, BloodPressureRequestDto dto) {
        // 상태 계산 (plain text 기준)
        String status = computeStatus(dto.getSystolic(), dto.getDiastolic());

        // payload 구성 (암호화 전 값)
        Map<String, Object> payload = new HashMap<>();
        payload.put("startTime", dto.getStartTime());
        payload.put("systolic", dto.getSystolic());
        payload.put("diastolic", dto.getDiastolic());

        // 민감 데이터 암호화
        Map<String, Object> encryptedPayload = payloadEncryptor.encryptBloodPressurePayload(payload);

        // 메시지 생성: 상태는 plain text로 포함 (암호화되지 않음)
        LifelogMessageDto message = LifelogMessageDto.builder()
                .userId(userId)
                .ci("ci_test")
                .logType("BLOOD_PRESSURE")
                .payload(encryptedPayload)
                .status(status)
                .build();

        // 메시지 전송
        lifelogProducerService.sendLifelogMessage(message);
        log.info("BloodPressureService.save 처리 완료 - userId: {}", userId);
    }


    @Transactional(readOnly = true)
    public List<UserBloodPressureResponseDto> getUserBloodPressure(String userId) {
        Users user = usersRepository.findById_UserId(userId);

        UserInfoResponseDto userDto = UserInfoResponseDto.builder()
                .userName(user.getUserName())
                .gender(user.getGender())
                .ci(user.getCi())
                .build();

        // lifelog-service에서 ci를 기반으로 혈압 로그 데이터 조회 (RabbitMQ 동기 응답)
        List<BloodPressureResponseDto> bloodPressureLog = getBloodPressureLog(userDto.getCi());

        return bloodPressureLog.stream()
                .map(bp -> UserBloodPressureResponseDto.builder()
                        .userName(userDto.getUserName())
                        .gender(userDto.getGender())
                        .logType(bp.getLogType())
                        .isActive(bp.getIsActive())
                        .systolic(bp.getSystolic())
                        .diastolic(bp.getDiastolic())
                        .startTime(bp.getStartTime())
                        .createdAt(bp.getCreatedAt())
                        .updatedAt(bp.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
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
