package com.kafkaproducerdemo;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;


import java.io.IOException;

public class UserDeserializer implements Deserializer<User> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public User deserialize(String s, byte[] data) {
        try{
            return objectMapper.readValue(data, User.class);
        }
        catch (Exception e){
            throw new RuntimeException("Error in UserDeserializer class", e);
        }

    }
}


