package com.kafkaproducerdemo;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@EnableKafkaStreams
public class EventStreamReceiverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventStreamReceiverApplication.class, args);
    }

    @KafkaListener(id = "IRC2D_1", topics = "InfyRail_C_To_D_Break_Warning")
    public void listenToTopic(ConsumerRecord<String, Long> message){
        System.out.println("Receiving " + message.value() + " % of Break efficiency " + message.key() + " times ");

    }


}
