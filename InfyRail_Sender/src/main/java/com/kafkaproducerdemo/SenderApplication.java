package com.kafkaproducerdemo;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenderApplication.class, args);
	}

	@Bean
	public NewTopic topic(){
		return TopicBuilder.name("InfyRail_CityA_To_CityB").partitions(1)
				.replicas(1).build();
	}

	@Bean
	public NewTopic topic2(){
		return TopicBuilder.name("InfyRail_CityA_To_CityB_output").partitions(1)
				.replicas(1).build();
	}

	@Bean
	public ApplicationRunner sendMessage(KafkaTemplate<String, String> template){
		return args -> {
			for(int i = 0; i<500; i++){
				float f = Math.round(Math.random()*100);
				template.send("InfyRail_CityA_To_CityB", " Current Speed of Train is:" +
						f + " Kmph.");
				System.out.println("Speed "  + f + " Information sent successfully for " + (i+1) + " of 500 times");
				Thread.sleep(3000,0);
			}
		};
	}
}
