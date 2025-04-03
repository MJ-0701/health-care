package com.example.userservice.service;

import com.example.storagemodule.dto.LifelogMessageDto;
import com.example.userservice.messaging.LifelogProducerService;
import com.example.userservice.security.PayloadEncryptor;
import com.example.userservice.web.dto.BloodPressureRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BloodPressureService {

    private final LifelogProducerService lifelogProducerService;
    private final PayloadEncryptor payloadEncryptor;

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
                .ci("TODO :: CI값 처리")
                .logType("BLOOD_PRESSURE")
                .payload(encryptedPayload)
                .status(status)
                .build();

        // 메시지 전송
        lifelogProducerService.sendLifelogMessage(message);
        log.info("BloodPressureService.save 처리 완료 - userId: {}", userId);
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
