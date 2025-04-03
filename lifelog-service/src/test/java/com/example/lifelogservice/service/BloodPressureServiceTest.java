package com.example.lifelogservice.service;

import com.example.lifelogservice.domain.repository.LifelogCustomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BloodPressureServiceTest {

    @Mock
    private LifelogCustomRepository lifelogCustomRepository;

    @InjectMocks
    private BloodPressureService bloodPressureService;

    @Test
    void testGetUserBloodPressure() {
        // given
        String userCi = "ci_user1";
        LocalDateTime now = LocalDateTime.now();

        // payload는 이미 JsonPayloadConverter에 의해 Map<String, Object>로 변환되어 있다고 가정
        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("systolic", 120.5);
        payloadMap.put("diastolic", 80.3);

        // LifelogResponseDto는 @Builder를 사용하여 생성 (setter 없이)
        LifelogResponseDto lifelogDto = LifelogResponseDto.builder()
                .logType("BLOOD_PRESSURE")
                .isActive(true)
                .payload(payloadMap)
                .startTime(now)
                .createdAt(now)
                .updatedAt(now)
                .build();

        when(lifelogCustomRepository.getUserBloodPressure(userCi))
                .thenReturn(List.of(lifelogDto));

        // when
        List<BloodPressureResponseDto> responseList = bloodPressureService.getUserBloodPressure(userCi);

        // then
        assertNotNull(responseList);
        assertEquals(1, responseList.size());
        BloodPressureResponseDto response = responseList.get(0);
        assertEquals("BLOOD_PRESSURE", response.getLogType());
        assertTrue(response.getIsActive());
        assertEquals(120.5, response.getSystolic());
        assertEquals(80.3, response.getDiastolic());
        assertEquals(now, response.getStartTime());
        assertEquals(now, response.getCreatedAt());
        assertEquals(now, response.getUpdatedAt());
    }

}