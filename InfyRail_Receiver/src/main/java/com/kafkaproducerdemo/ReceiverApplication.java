package com.kafkaproducerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class ReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiverApplication.class, args);
	}

	@KafkaListener(id = "IRA2B_1", topics = "InfyRail_CityA_To_CityB")
	public void listenToTopic(String message){
		System.out.println("Message Received is :" + message);
	}

}
