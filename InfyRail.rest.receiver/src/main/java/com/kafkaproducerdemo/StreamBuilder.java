package com.kafkaproducerdemo;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class StreamBuilder {

    @Autowired
    public void doProcess(StreamsBuilder streamBuilder ){

        KStream<String, String> trainData = streamBuilder.stream("InfyRail_C_To_D", Consumed.with(Serdes.String(), Serdes.String()));
        KTable<String, Long> trainDatum = trainData.filter((key, value) -> String.valueOf(key).equalsIgnoreCase("BreakEfficiency"))
                .filter((key, value) -> Long.parseLong(value) < 70)
                .groupBy((key, value) -> value, Grouped.with(Serdes.String(), Serdes.String()))
                .count(Materialized.as("breakData"));
                trainDatum.toStream().to("InfyRail_C_To_D_Break_Warning", Produced.with(Serdes.String(), Serdes.Long()));

    }
}
