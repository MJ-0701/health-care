package com.example.userservice.messaging;

import com.example.storagemodule.dto.request.LifelogMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

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
                        // 조회용 메시지의 __TypeId__를 강제로 String으로 지정
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
}