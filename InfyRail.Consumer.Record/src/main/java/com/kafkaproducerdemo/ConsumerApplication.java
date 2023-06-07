package com.kafkaproducerdemo;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("InfyRail_C_To_D").partitions(6)
                .replicas(1).build();
    }


    @Bean
    public ApplicationRunner sendMessage(KafkaTemplate<String, String> template) {
        return args -> {
            for(;;){
                template.send("InfyRail_C_To_D", "Speed", String.valueOf(Math.round(Math.random()*1000)));
                template.send("InfyRail_C_To_D", "BreakEfficiency", String.valueOf(Math.round(Math.random()*100)));
                System.out.println("Sender Module:- ");
                System.out.println("Speed and Breaking Efficiency information sent successfully");
                Thread.sleep(3000,0);
            }
        };
    }

    @KafkaListener(id = "IRC2D_1", topics = "InfyRail_C_To_D")
    public void listenToTopic(ConsumerRecord<String, String> record){
        System.out.println("Receiver Module -");
        System.out.println("Key:- " + record.key());
        System.out.println("Value:- "+ record.value());
        System.out.println("partition:- "+ record.partition());
        System.out.println("timestamp:- "+ record.timestamp());
        System.out.println("Topic:- " + record.topic());
        System.out.println("offset:- "+ record.offset());
        System.out.println("---------------------------------------------------------");
    }


}
