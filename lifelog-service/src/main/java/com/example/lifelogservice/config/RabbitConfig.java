package com.example.lifelogservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "lifelog.exchange";

    public static final String ROUTING_KEY_BP = "lifelog.bloodpressure";
    public static final String ROUTING_KEY_STEP = "lifelog.stepcount";
    public static final String ROUTING_KEY_WEIGHT = "lifelog.weight";

    public static final String QUEUE_BP = "lifelog.bloodpressure.queue";
    public static final String QUEUE_STEP = "lifelog.stepcount.queue";
    public static final String QUEUE_WEIGHT = "lifelog.weight.queue";

    @Bean
    public TopicExchange lifelogExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue bloodPressureQueue() {
        return new Queue(QUEUE_BP);
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
}
