package com.example.lifelogservice.messaging;

import com.example.lifelogservice.config.RabbitConfig;
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

    @RabbitListener(queues = RabbitConfig.QUEUE_BP) // "lifelog.bloodpressure.queue"
    public void consumeBloodPressure(LifelogMessageDto message) {
        try {
            log.info("🔔 혈압 메시지 수신됨: {}", message);

            // 메시지 파싱
            String ci = message.getCi();
            String logType = message.getLogType();
            Object startTimeStr = message.getPayload().get("startTime");
            ZonedDateTime startTime = ZonedDateTime.parse(startTimeStr.toString());

            String payloadJson = new ObjectMapper().writeValueAsString(message.getPayload());

            // 엔티티 생성 및 저장
            Lifelog lifelog = Lifelog.builder()
                    .ci(ci)
                    .logType(logType)
                    .payload(payloadJson)
                    .startTime(startTime)
                    .build();

            lifelogRepository.save(lifelog);
            log.info("✅ lifelog 저장 완료: {}", lifelog.getId());

        } catch (Exception e) {
            log.error("❌ lifelog 저장 실패", e);
        }
    }
}
