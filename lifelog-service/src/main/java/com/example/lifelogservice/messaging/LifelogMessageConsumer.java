package com.example.lifelogservice.messaging;

import com.example.lifelogservice.config.RabbitConfig;
import com.example.lifelogservice.domain.entity.Lifelog;
import com.example.lifelogservice.domain.entity.LogType;
import com.example.lifelogservice.domain.repository.LifelogRepository;
import com.example.lifelogservice.web.dto.LifelogMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class LifelogMessageConsumer {

    private final LifelogRepository lifelogRepository;

    @RabbitListener(queues = RabbitConfig.QUEUE_BP)
    public void consumeBloodPressure(LifelogMessageDto message) {
        try {
            log.info("🔔 혈압 메시지 수신됨: {}", message);

            // 필드 추출
            String userId = message.getUserId();
            String ci = message.getCi();
            LogType logType = LogType.valueOf(message.getLogType());
            ZonedDateTime startTime = ZonedDateTime.parse(message.getPayload().get("startTime").toString());

            // 엔티티 생성
            Lifelog lifelog = Lifelog.builder()
                    .userId(userId)
                    .ci(ci)
                    .logType(logType)
                    .startTime(startTime)
                    .payload(message.getPayload()) // Map 그대로 넣으면 @Convert가 처리
                    .isActive(true)
                    .status("NORMAL")
                    .build();

            lifelogRepository.save(lifelog);
            log.info("✅ lifelog 저장 완료: {}", lifelog.getId());

        } catch (Exception e) {
            log.error("❌ lifelog 저장 실패", e);
        }
    }

}
