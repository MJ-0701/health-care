package com.example.userservice.service;

import com.example.userservice.messaging.LifelogProducerService;
import com.example.userservice.security.PayloadEncryptor;
import com.example.userservice.web.dto.request.BloodPressureRequestDto;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
        "eureka.client.enabled=false",
        "eureka.client.register-with-eureka=false",
        "eureka.client.fetch-registry=false"
})
class BloodPressureServiceTest {

    @Autowired
    private BloodPressureService bloodPressureService;

    @MockBean
    private LifelogProducerService lifelogProducerService;

    @MockBean
    private PayloadEncryptor payloadEncryptor;

    @Test
    void testSave_shouldSendEncryptedMessage() {
        // 테스트 데이터 설정
        String userId = "TEST_USER";
        BloodPressureRequestDto dto = BloodPressureRequestDto.builder()
                .startTime("2025-04-02T20:00:00Z")
                .systolic(120.5)
                .diastolic(80.3)
                .build();

        Map<String, Object> originalPayload = new HashMap<>();
        originalPayload.put("startTime", dto.getStartTime());
        originalPayload.put("systolic", dto.getSystolic());
        originalPayload.put("diastolic", dto.getDiastolic());

        Map<String, Object> encryptedPayload = new HashMap<>();
        encryptedPayload.put("startTime", dto.getStartTime());
        encryptedPayload.put("systolic", "encrypted_" + dto.getSystolic());
        encryptedPayload.put("diastolic", "encrypted_" + dto.getDiastolic());

        when(payloadEncryptor.encryptBloodPressurePayload(any(Map.class)))
                .thenReturn(encryptedPayload);

        // when
        bloodPressureService.save(userId, dto);

        // then
        ArgumentCaptor<LifelogMessageDto> captor = ArgumentCaptor.forClass(LifelogMessageDto.class);
        verify(lifelogProducerService, times(1)).sendLifelogMessage(captor.capture());
        LifelogMessageDto message = captor.getValue();
        assertThat(message.getUserId()).isEqualTo(userId);
    }
}