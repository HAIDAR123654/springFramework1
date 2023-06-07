package com.kafkaproducerdemo;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class FilterData {

    @Autowired
    public void setStreamBuilder(StreamsBuilder streamBuilder ){
        System.out.println("-----------This is a test---------------");
        KStream<String, String> trainData = streamBuilder.stream("InfyRail_C_To_D",
                Consumed.with(Serdes.String(), Serdes.String()));
        trainData.filter((key, value) -> String.valueOf(key).equalsIgnoreCase("BreakEfficiency"))
                .filter((key, value) -> Long.parseLong(value) < 70)
                .to("InfyRail_C_To_D_Break_Warning", Produced.with(Serdes.String(), Serdes.String()));

    }
}
