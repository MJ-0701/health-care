package com.example.lifelogservice.messaging;

import com.example.lifelogservice.config.RabbitMQConfig;
import com.example.lifelogservice.service.BloodPressureService;
import com.example.storagemodule.dto.request.LifelogMessageDto;
import com.example.storagemodule.dto.request.TimelineQueryDto;
import com.example.storagemodule.dto.response.BloodPressureResponseDto;
import com.example.storagemodule.dto.response.BloodPressureTimelineDto;
import com.example.storagemodule.dto.response.BloodPressureTimelineResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class LifelogMessageConsumer {

    private final BloodPressureService bloodPressureService;
    private final RabbitTemplate rabbitTemplate;

    // 저장용: LifelogMessageDto를 수신하고, DB 저장은 service에 위임
    @RabbitListener(queues = RabbitMQConfig.QUEUE_BP_SAVE)
    public void consumeBloodPressureSave(LifelogMessageDto message) {
        try {
            log.info("🔔 혈압 저장 메시지 수신됨: {}", message);
            bloodPressureService.saveBloodPressure(message);
        } catch (Exception e) {
            log.error("❌ lifelog 저장 실패", e);
            try {
                rabbitTemplate.convertAndSend(RabbitMQConfig.DLX, RabbitMQConfig.DLQ_BP_ROUTING_KEY, message);
                log.info("✅ 메시지가 DLQ로 전송되었습니다. routingKey: {}, message: {}", RabbitMQConfig.DLQ_BP_ROUTING_KEY, message);
            } catch (Exception dlqEx) {
                log.error("DLQ 전송 실패", dlqEx);
            }
        }
    }

    // 조회용: 단순 String(ci)를 수신하고, 조회 결과를 반환 (RPC 방식)
    @RabbitListener(queues = RabbitMQConfig.QUEUE_BP_QUERY)
    public List<BloodPressureResponseDto> consumeBloodPressureQuery(@org.springframework.messaging.handler.annotation.Payload String ci) {
        log.info("🔔 혈압 조회 메시지 수신됨: ci = {}", ci);
        List<BloodPressureResponseDto> logs = bloodPressureService.getUserBloodPressure(ci);
        log.info("조회 결과: {} 건", logs.size());
        return logs;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_BP_TIMELINE_QUERY)
    public BloodPressureTimelineResponseDto consumeBloodPressureTimelineQuery(@Payload TimelineQueryDto queryDto) {
        log.info("🔔 혈압 타임라인 조회 메시지 수신됨: {}", queryDto);
        BloodPressureTimelineResponseDto timeline = bloodPressureService.getUserBloodPressureTimeline(queryDto.getCi(), queryDto.getPeriodType());
        return timeline;
    }
}