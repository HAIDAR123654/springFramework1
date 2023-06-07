package com.kafkaproducerdemo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);
    @KafkaListener(id = "myId", topics = "Json_Test_filter", groupId = "myGroup")
    public void consume(User user){
        LOGGER.info(String.format("Filtered User is ->%s", user));
    }

}
