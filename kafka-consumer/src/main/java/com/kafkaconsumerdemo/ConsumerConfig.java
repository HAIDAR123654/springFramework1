package com.kafkaconsumerdemo;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public class ConsumerConfig extends AbstractConfig {

    public static final ConfigDef CONFIG = new ConfigDef();
    
    public static final String GROUP_ID_CONFIG = "group.id";
    public ConsumerConfig(ConfigDef definition, Map<?, ?> originals, Map<String, ?> configProviderProps, boolean doLog) {
        super(definition, originals, configProviderProps, doLog);
    }
}
