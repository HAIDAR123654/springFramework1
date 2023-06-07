package com.kafkaproducerdemo;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class EventStreamSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventStreamSenderApplication.class, args);
    }

    @Bean
    public NewTopic topic2(){
        return TopicBuilder.name("InfyRail_C_To_D").partitions(6).replicas(1).build();
    }

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("InfyRail_C_To_D_Break_Warning").partitions(1).replicas(1).build();
    }

    @Bean
    public ApplicationRunner sendMessage(KafkaTemplate<String, String> template) {
        return args -> {
            for(;;){
                String speed = String.valueOf(Math.round(Math.random()*1000));
                String brkData = String.valueOf(Math.round(Math.random()*100));
                template.send("InfyRail_C_To_D", "Speed", speed);
                template.send("InfyRail_C_To_D", "BreakEfficiency", brkData);
                System.out.println("Sending speed = " + speed + " Kmph and Break efficiency = " + brkData+ " %. ");
                Thread.sleep(3000,0);
            }
        };
    }


}
