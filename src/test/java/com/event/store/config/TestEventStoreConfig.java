package com.event.store.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EventStoreConfig.class)
public class TestEventStoreConfig {
    @Autowired
    EventStoreConfig eventStoreConfig;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testEventStoreConfig(){
        assertNotNull(eventStoreConfig);
        assertNotNull(objectMapper);
    }
}
