package com.example.lifelogservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

    // 라우팅 키 정의 (저장용과 조회용 분리)
    public static final String ROUTING_KEY_BP_SAVE = "lifelog.bloodpressure.save";
    public static final String ROUTING_KEY_BP_QUERY = "lifelog.bloodpressure.query";
    public static final String ROUTING_KEY_BP_TIMELINE_QUERY = "lifelog.bloodpressure.timeline.query";
    public static final String ROUTING_KEY_STEP = "lifelog.stepcount";
    public static final String ROUTING_KEY_WEIGHT = "lifelog.weight";

    // 큐 이름 정의
    public static final String QUEUE_BP_SAVE = "lifelog.bloodpressure.save.queue";
    public static final String QUEUE_BP_QUERY = "lifelog.bloodpressure.query.queue";
    public static final String QUEUE_BP_TIMELINE_QUERY = "lifelog.bloodpressure.timeline.query.queue";
    public static final String QUEUE_STEP = "lifelog.stepcount.queue";
    public static final String QUEUE_WEIGHT = "lifelog.weight.queue";

    // Dead Letter 관련 상수
    public static final String DLX = "lifelog.exchange.dlx";
    public static final String DLQ_BP = "lifelog.bloodpressure.dlq";
    public static final String DLQ_BP_ROUTING_KEY = "lifelog.bloodpressure.dlq";

    @Bean
    public MessageConverter jsonMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // JavaTimeModule 등록
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTrustedPackages("com.example.storagemodule.dto");
        converter.setJavaTypeMapper(typeMapper);

        return converter;
    }


    @Bean
    public TopicExchange lifelogExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue bloodPressureSaveQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DLX);
        args.put("x-dead-letter-routing-key", DLQ_BP_ROUTING_KEY);
        return new Queue(QUEUE_BP_SAVE, true, false, false, args);
    }

    @Bean
    public Queue bloodPressureQueryQueue() {
        return new Queue(QUEUE_BP_QUERY);
    }

    @Bean
    public Queue bloodPressureTimelineQueryQueue() {
        return new Queue(QUEUE_BP_TIMELINE_QUERY);
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
    public Binding bloodPressureSaveBinding() {
        return BindingBuilder.bind(bloodPressureSaveQueue()).to(lifelogExchange()).with(ROUTING_KEY_BP_SAVE);
    }

    @Bean
    public Binding bloodPressureQueryBinding() {
        return BindingBuilder.bind(bloodPressureQueryQueue()).to(lifelogExchange()).with(ROUTING_KEY_BP_QUERY);
    }

    @Bean
    public Binding bloodPressureTimelineQueryBinding() {
        return BindingBuilder.bind(bloodPressureTimelineQueryQueue())
                .to(lifelogExchange())
                .with(ROUTING_KEY_BP_TIMELINE_QUERY);
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