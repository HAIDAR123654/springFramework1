package com.kafkaconsumerdemo.service;

import com.kafkaconsumerdemo.KafkaConstants;
import com.kafkaconsumerdemo.dto.Customer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service("customerService")
public class CustomerService {

    @KafkaListener(topics = KafkaConstants.TOPIC, groupId = KafkaConstants.GROUP_ID)
    public Customer listener(Customer c){
        System.out.println("***Msg received from Kafka Topic ::" + c);
        return c;
    }
}
