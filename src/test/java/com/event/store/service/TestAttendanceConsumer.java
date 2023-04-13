package com.event.store.service;

import com.event.store.entity.EmployeeEntity;
import com.event.store.model.KafkaPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class TestAttendanceConsumer {

    @InjectMocks
    private AttendanceConsumer attendanceConsumer;
    @Mock
    private  ObjectMapper objectMapper;
    @Mock
    private EventStoreService eventStoreService;
    @Captor
    ArgumentCaptor<EmployeeEntity> kafkaPayloadArgumentCaptor;
    KafkaPayload kafkaPayload;

    @BeforeEach
    void setUp(){
        openMocks(this);
        kafkaPayload = KafkaPayload.builder().attendance(5.00D).empId(433).name("shiva").build();
    }

    @Test
    void testAttendanceConsumer () throws JsonProcessingException {
        String payLoad = new ObjectMapper().writeValueAsString(kafkaPayload);
        when(objectMapper.readValue(payLoad,KafkaPayload.class)).thenReturn(kafkaPayload);
        attendanceConsumer.attendanceConsumer(payLoad);
        verify(objectMapper,times(1)).readValue(payLoad,KafkaPayload.class);
        verify(eventStoreService,times(1)).saveAttendance(kafkaPayloadArgumentCaptor.capture());
        EmployeeEntity employeeEntity = kafkaPayloadArgumentCaptor.getValue();
        assertNotNull(employeeEntity);
        assertEquals(433,employeeEntity.getEmpId());
        assertEquals("shiva",employeeEntity.getName());
        assertEquals("Half day",employeeEntity.getAttendance());

    }
}
