package com.kafkaproducerdemo;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StreamBuilder {
private static final Logger LOGGER = LoggerFactory.getLogger(StreamBuilder.class);
    @Autowired
    public void doProcess(StreamsBuilder streamsBuilder){
        LOGGER.info("doProcess started");
        Serde<User> userSerde = Serdes.serdeFrom(new UserSerializer(), new UserDeserializer());
        KStream<String, User> data = streamsBuilder.stream("Json_Test",
                Consumed.with(Serdes.String(),userSerde ));
        KStream<String, User> filterData = data.filter((key, value) -> Integer.parseInt(value.getAge()) >= 18);
        filterData.to("Json_Test_filter");
        LOGGER.info("doProcess ended");
    }
}
