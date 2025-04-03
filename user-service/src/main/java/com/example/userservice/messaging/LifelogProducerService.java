package com.example.userservice.messaging;

import com.example.storagemodule.dto.request.LifelogMessageDto;
import com.example.storagemodule.dto.request.TimelineQueryDto;
import com.example.storagemodule.dto.response.BloodPressureTimelineDto;
import com.example.storagemodule.dto.response.BloodPressureTimelineResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LifelogProducerService {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "lifelog.exchange";

    // 저장용 메시지 전송
    public void sendLifelogMessage(LifelogMessageDto dto) {
        String routingKey = LifelogRoutingKeyMapper.getRoutingKey(dto.getLogType(), false);
        try {
            rabbitTemplate.convertAndSend(EXCHANGE, routingKey, dto);
            log.info("Lifelog 메시지 전송 성공 [{}]: {}", routingKey, dto);
        } catch (Exception e) {
            log.error("Lifelog 메시지 전송 실패 [{}]: {}", routingKey, dto, e);
            throw new RuntimeException("메시지 전송 중 오류 발생", e);
        }
    }

    // 조회용 메시지 전송
    public Object getUserBloodPressureLog(String ci, String logType) {
        String routingKey = LifelogRoutingKeyMapper.getRoutingKey(logType, true);
        try {
            Object response = rabbitTemplate.convertSendAndReceive(
                    EXCHANGE,
                    routingKey,
                    ci,
                    message -> {
                        message.getMessageProperties().setHeader("__TypeId__", "java.lang.String");
                        return message;
                    }
            );
            log.info("메시지 응답 수신 성공 [{}]: {}", routingKey, response);
            return response;
        } catch (Exception e) {
            log.error("메시지 전송 또는 응답 수신 실패 [{}]: {}", routingKey, ci, e);
            throw new RuntimeException("메시지 전송 중 오류 발생", e);
        }
    }

    public BloodPressureTimelineResponseDto getUserBloodPressureTimeline(String ci, String periodType) {
        TimelineQueryDto queryDto = TimelineQueryDto.builder()
                .ci(ci)
                .periodType(periodType)
                .build();

        // 타임라인 조회용 라우팅 키: "BLOOD_PRESSURE_TIMELINE"
        String routingKey = LifelogRoutingKeyMapper.getRoutingKey("BLOOD_PRESSURE_TIMELINE", true);
        try {
            Object response = rabbitTemplate.convertSendAndReceive(
                    EXCHANGE,
                    routingKey,
                    queryDto,
                    message -> {
                        message.getMessageProperties().setHeader("__TypeId__", "com.example.storagemodule.dto.response.BloodPressureTimelineResponseDto");
                        return message;
                    }
            );
            log.info("타임라인 메시지 응답 수신 성공 [{}]: {}", routingKey, response);

            if (response instanceof BloodPressureTimelineResponseDto) {
                return (BloodPressureTimelineResponseDto) response;
            } else {
                throw new RuntimeException("타임라인 응답 타입이 올바르지 않습니다. 실제 응답 타입: " +
                        (response != null ? response.getClass().getName() : "null"));
            }
        } catch (Exception e) {
            log.error("타임라인 메시지 전송 또는 응답 수신 실패 [{}]: ci = {}", routingKey, ci, e);
            throw new RuntimeException("메시지 전송 중 오류 발생", e);
        }
    }

}