package com.example.lifelogservice;


import com.example.common.utils.CryptoUtil;
import com.example.lifelogservice.domain.entity.Lifelog;
import com.example.lifelogservice.domain.entity.LogType;
import com.example.lifelogservice.domain.repository.LifelogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final LifelogRepository lifelogRepository;

    @Override
    public void run(String... args) throws Exception {
        // 사용자 및 CI 정보
        String[] users = {"user1", "user2", "user3"};
        String[] cis = {"ci_user1", "ci_user2", "ci_user3"};

        // 2025년 3월 1일부터 3월 31일까지 (한 달치)
        LocalDate startDate = LocalDate.of(2025, 3, 1);
        LocalDate endDate = LocalDate.of(2025, 3, 31);

        // 3월 25일부터 3월 31일까지는 혈압 로그를 하루에 2~4개 생성하도록 설정
        Set<LocalDate> extraBpDays = new HashSet<>();
        for (LocalDate d = LocalDate.of(2025, 3, 25); !d.isAfter(LocalDate.of(2025, 3, 31)); d = d.plusDays(1)) {
            extraBpDays.add(d);
        }

        Random rand = new Random();

        for (int i = 0; i < users.length; i++) {
            String userId = users[i];
            String ci = cis[i];

            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                // 혈압 로그 생성 (7시~9시 사이 랜덤)
                int bpEntries = extraBpDays.contains(date) ? rand.nextInt(3) + 2 : 1;
                for (int j = 0; j < bpEntries; j++) {
                    // 기본 혈압 값에 약간의 랜덤 변동 추가
                    double baseSystolic = 120.0 + (date.getDayOfMonth() % 5) * 0.1;
                    double baseDiastolic = 80.0 + (date.getDayOfMonth() % 3) * 0.1;
                    double systolicVal = baseSystolic + rand.nextDouble() * 5;    // 0~5 사이 증가
                    double diastolicVal = baseDiastolic + rand.nextDouble() * 3;  // 0~3 사이 증가
                    String systolicStr = String.valueOf(systolicVal);
                    String diastolicStr = String.valueOf(diastolicVal);

                    // 혈압 측정 시간: 8시 기준에서 최대 120분까지 랜덤 오프셋 추가
                    LocalDateTime bpTime = date.atTime(8, 0).plusMinutes(rand.nextInt(121));
                    String bpTimeStr = bpTime.toString() + ":00Z"; // 예: "2025-03-01T08:00:00Z"

                    Map<String, Object> bpAdditional = new HashMap<>();
                    bpAdditional.put("systolic", CryptoUtil.encrypt(systolicStr));
                    bpAdditional.put("diastolic", CryptoUtil.encrypt(diastolicStr));
                    Map<String, Object> bpPayload = createPayload("BLOOD_PRESSURE", bpTimeStr, bpAdditional);

                    Lifelog bpLog = Lifelog.builder()
                            .id(UUID.randomUUID())
                            .userId(userId)
                            .ci(ci)
                            .isActive(true)
                            .logType(LogType.valueOf("BLOOD_PRESSURE"))
                            .payload(bpPayload)
                            .status("NORMAL_BP")
                            .startTime(bpTime)
                            .build();
                    lifelogRepository.save(bpLog);
                }

                // STEP_COUNT 로그 생성 (하루 1건, 07:00 기준)
                LocalDateTime stepTime = date.atTime(7, 0);
                String stepTimeStr = stepTime.toString() + ":00Z";
                int stepCount = 10000 + (date.getDayOfMonth() % 3) * 500;
                double calorie = 300.0 + (date.getDayOfMonth() % 4) * 10.0;
                Map<String, Object> stepAdditional = new HashMap<>();
                stepAdditional.put("stepCount", stepCount);
                stepAdditional.put("calorie", calorie);
                Map<String, Object> stepPayload = createPayload("STEP_COUNT", stepTimeStr, stepAdditional);

                Lifelog stepLog = Lifelog.builder()
                        .id(UUID.randomUUID())
                        .userId(userId)
                        .ci(ci)
                        .isActive(true)
                        .logType(LogType.valueOf("STEP_COUNT"))
                        .payload(stepPayload)
                        .status("NORMAL")
                        .startTime(stepTime)
                        .build();
                lifelogRepository.save(stepLog);

                // WEIGHT 로그 생성 (하루 1건, 09:00 기준)
                LocalDateTime weightTime = date.atTime(9, 0);
                String weightTimeStr = weightTime.toString() + ":00Z";
                Map<String, Object> weightAdditional = getStringObjectMap(userId, date);
                Map<String, Object> weightPayload = createPayload("WEIGHT", weightTimeStr, weightAdditional);

                Lifelog weightLog = Lifelog.builder()
                        .id(UUID.randomUUID())
                        .userId(userId)
                        .ci(ci)
                        .isActive(true)
                        .logType(LogType.valueOf("WEIGHT"))
                        .payload(weightPayload)
                        .status("UNKNOWN")
                        .startTime(weightTime)
                        .build();
                lifelogRepository.save(weightLog);
            }
        }
        System.out.println("한 달치 데이터 초기 삽입 완료");
    }

    private static Map<String, Object> getStringObjectMap(String userId, LocalDate date) {
        double weightVal;
        double heightVal;
        if (userId.equals("user1")) {
            weightVal = 68.5 + (date.getDayOfMonth() % 2) * 0.1;
            heightVal = 172.3;
        } else if (userId.equals("user2")) {
            weightVal = 75.0 + (date.getDayOfMonth() % 2) * 0.2;
            heightVal = 175.0;
        } else {
            weightVal = 60.0 + (date.getDayOfMonth() % 2) * 0.1;
            heightVal = 165.0;
        }
        Map<String, Object> weightAdditional = new HashMap<>();
        weightAdditional.put("weight", weightVal);
        weightAdditional.put("height", heightVal);
        return weightAdditional;
    }

    private Map<String, Object> createPayload(String logType, String timeStr, Map<String, Object> additionalFields) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("logType", logType);
        payload.put("createTime", timeStr);
        payload.put("updateTime", timeStr);
        payload.put("startTime", timeStr);
        payload.put("isActive", true);
        if (additionalFields != null) {
            payload.putAll(additionalFields);
        }
        return payload;
    }
}