package com.example.lifelogservice.messaging;

import com.example.lifelogservice.config.RabbitMQConfig;
import com.example.lifelogservice.domain.entity.Lifelog;
import com.example.lifelogservice.domain.entity.LogType;
import com.example.lifelogservice.domain.repository.LifelogRepository;
import com.example.storagemodule.dto.LifelogMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class LifelogMessageConsumer {

    private final LifelogRepository lifelogRepository;
    private final RabbitTemplate rabbitTemplate;

    // Dead Letter 관련 상수 (RabbitConfig와 일치해야 합니다.)
    private static final String DLX = "lifelog.exchange.dlx";
    private static final String DLQ_ROUTING_KEY = "lifelog.bloodpressure.dlq";

    @RabbitListener(queues = RabbitMQConfig.QUEUE_BP)
    public void consumeBloodPressure(LifelogMessageDto message) {
        try {
            log.info("🔔 혈압 메시지 수신됨: {}", message);

            // 필수 필드 검증
            if (message.getUserId() == null || message.getLogType() == null || message.getPayload() == null) {
                throw new IllegalArgumentException("메시지 필수 필드(userId, logType, payload) 누락");
            }

            // 필드 추출
            String userId = message.getUserId();
            String ci = message.getCi();  // ci 값은 그대로 유지
            LogType logType;
            try {
                logType = LogType.valueOf(message.getLogType());
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("지원하지 않는 logType: " + message.getLogType(), ex);
            }

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

            // user-service에서 계산한 상태(status)를 그대로 사용
            String status = message.getStatus();
            if (status == null) {
                status = "UNKNOWN";
            }

            // 엔티티 생성 (엔티티의 startTime 필드를 LocalDateTime으로 선언)
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

        } catch (Exception e) {
            log.error("❌ lifelog 저장 실패", e);
            // DLQ 로직: 메시지 처리 실패 시 Dead Letter Exchange를 통해 DLQ로 메시지를 전송
            try {
                rabbitTemplate.convertAndSend(DLX, DLQ_ROUTING_KEY, message);
                log.info("✅ 메시지가 DLQ로 전송되었습니다. routingKey: {}, message: {}", DLQ_ROUTING_KEY, message);
            } catch (Exception dlqEx) {
                log.error("DLQ 전송 실패", dlqEx);
            }
        }
    }
}