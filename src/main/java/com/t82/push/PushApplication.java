package com.t82.push;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class PushApplication {

	public static void main(String[] args) {
		SpringApplication.run(PushApplication.class, args);
	}
	@Bean
	public RecordMessageConverter converter(){
		return new JsonMessageConverter();
	}
}
