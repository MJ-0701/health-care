package com.example.userservice.messaging;

import com.example.userservice.web.dto.LifelogMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LifelogProducerService {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE = "lifelog.exchange";

    public void sendLifelogMessage(LifelogMessageDto dto) {
        String routingKey = LifelogRoutingKeyMapper.getRoutingKey(dto.getLogType());
        try {
            rabbitTemplate.convertAndSend(EXCHANGE, routingKey, dto);
            log.info("Lifelog 메시지 전송 성공 [{}]: {}", routingKey, dto);
        } catch (Exception e) {
            log.error("Lifelog 메시지 전송 실패 [{}]: {}", routingKey, dto, e);
            throw new RuntimeException("메시지 전송 중 오류 발생", e);
        }
    }
}
