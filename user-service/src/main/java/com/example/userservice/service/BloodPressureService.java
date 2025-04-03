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
        String status = computeStatus(dto.getDiastolic());

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

    private String computeStatus(Double diastolic) {
        // diastolic 값에 따라 상태를 계산 (plain text 값 사용)
        if (diastolic >= 80 && diastolic <= 90) {
            return "NORMAL";
        } else if (diastolic >= 91 && diastolic <= 100) {
            return "WARNING";
        } else if (diastolic > 100) {
            return "DANGER";
        }
        return "UNKNOWN";
    }
}
