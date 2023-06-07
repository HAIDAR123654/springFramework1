package com.kafkaproducerdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "/publish")
    public ResponseEntity<String> sendUser(@RequestBody User user){
        userService.sendJson(user);
        return ResponseEntity.ok("Json message sent to kafka");
    }
}
