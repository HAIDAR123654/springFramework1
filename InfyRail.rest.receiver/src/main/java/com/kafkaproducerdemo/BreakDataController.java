package com.kafkaproducerdemo;

import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/breakData")
public class BreakDataController {

    @Autowired
    private StreamsBuilderFactoryBean factoryBean;

    @GetMapping("/{input}")
    public String getData(@PathVariable("input") String inputPercent){
        final KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        final ReadOnlyKeyValueStore<String, Long> dataReturn =
                kafkaStreams.store(StoreQueryParameters.fromNameAndType("breakData", QueryableStoreTypes.keyValueStore()));
        return "The Train recorded "+ inputPercent + "% of break efficiency "+ dataReturn.get(inputPercent) + " times ";
    }
}
