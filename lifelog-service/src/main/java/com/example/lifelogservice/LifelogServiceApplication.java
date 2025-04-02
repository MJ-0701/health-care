package com.example.lifelogservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class LifelogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifelogServiceApplication.class, args);
	}

}
