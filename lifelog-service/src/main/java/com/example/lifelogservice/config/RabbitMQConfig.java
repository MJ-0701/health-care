package com.example.lifelogservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "lifelog.exchange";

    public static final String ROUTING_KEY_BP = "lifelog.bloodpressure";
    public static final String ROUTING_KEY_STEP = "lifelog.stepcount";
    public static final String ROUTING_KEY_WEIGHT = "lifelog.weight";

    public static final String QUEUE_BP = "lifelog.bloodpressure.queue";
    public static final String QUEUE_STEP = "lifelog.stepcount.queue";
    public static final String QUEUE_WEIGHT = "lifelog.weight.queue";

    // Dead Letter 관련 상수
    public static final String DLX = "lifelog.exchange.dlx";
    public static final String DLQ_BP = "lifelog.bloodpressure.dlq";
    public static final String DLQ_BP_ROUTING_KEY = "lifelog.bloodpressure.dlq";

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        // 신뢰하는 패키지 설정: storage-module의 DTO가 포함된 패키지
        typeMapper.setTrustedPackages("com.example.storagemodule.dto");
        converter.setJavaTypeMapper(typeMapper);

        return converter;
    }

    @Bean
    public TopicExchange lifelogExchange() {
        return new TopicExchange(EXCHANGE);
    }

    // 메인 혈압 큐에 DLX 설정 추가
    @Bean
    public Queue bloodPressureQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DLX);
        args.put("x-dead-letter-routing-key", DLQ_BP_ROUTING_KEY);
        return new Queue(QUEUE_BP, true, false, false, args);
    }

    @Bean
    public Queue stepCountQueue() {
        return new Queue(QUEUE_STEP);
    }

    @Bean
    public Queue weightQueue() {
        return new Queue(QUEUE_WEIGHT);
    }

    @Bean
    public Binding bloodPressureBinding() {
        return BindingBuilder.bind(bloodPressureQueue()).to(lifelogExchange()).with(ROUTING_KEY_BP);
    }

    @Bean
    public Binding stepCountBinding() {
        return BindingBuilder.bind(stepCountQueue()).to(lifelogExchange()).with(ROUTING_KEY_STEP);
    }

    @Bean
    public Binding weightBinding() {
        return BindingBuilder.bind(weightQueue()).to(lifelogExchange()).with(ROUTING_KEY_WEIGHT);
    }

    // Dead Letter Exchange(DLX) Bean
    @Bean
    public TopicExchange deadLetterExchange() {
        return new TopicExchange(DLX);
    }

    // Dead Letter Queue(DLQ) Bean for 혈압 메시지
    @Bean
    public Queue bloodPressureDLQ() {
        return new Queue(DLQ_BP);
    }

    // DLQ Binding: DLX와 DLQ를 라우팅 키로 연결
    @Bean
    public Binding bloodPressureDLQBinding() {
        return BindingBuilder.bind(bloodPressureDLQ()).to(deadLetterExchange()).with(DLQ_BP_ROUTING_KEY);
    }
}