package com.kafkaproducerdemo.service;

import com.kafkaproducerdemo.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerService")
public class CustomerService {

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    public String add(List<Customer> customers) {
        if (!customers.isEmpty()) {
            for (Customer c : customers) {
                kafkaTemplate.send("customer", c);
                System.out.println("********************** Message published to kafka topic ******************");
            }
        }
    return "Customer record added to kafka to kafka topic successfully";
    }
}
