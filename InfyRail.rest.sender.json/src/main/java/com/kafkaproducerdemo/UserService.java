package com.kafkaproducerdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final KafkaTemplate<String, User> kafkaTemplate;

    public UserService(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

//    public void sendJson(User data){
//        LOGGER.info(String.format("Message sent -> %s", data.toString()));
//        Message<User> message = MessageBuilder.withPayload(data)
//                .setHeader(KafkaHeaders.TOPIC, "Json_Test")
//                .build();
//        kafkaTemplate.send(message);
//    }
    public void sendJson(User data){
        kafkaTemplate.send("Json_Test", "user_data", data);
        LOGGER.info(String.format("User data sent -> %s", data.toString()));
    }

}
