package com.event.store.service;

import com.event.store.entity.EmployeeEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AttendanceConsumer {

    private static final String topic = "attendanceTopic";
    private final ObjectMapper objectMapper;
    private final EventStoreService eventStoreService;

    @KafkaListener(topics = topic,
            groupId = "attendanceGroup")
    public void attendanceConsumer(String attendanceData) {
        try {
            EmployeeEntity entity = convertStringToEmployee(attendanceData);
            eventStoreService.saveAttendance(entity);
        } catch (JsonProcessingException ex) {
            log.error("error to read attendance data: {} from topic {}", topic);
        }
    }

    private EmployeeEntity convertStringToEmployee(String attendanceData) throws JsonProcessingException {
        return objectMapper.readValue(attendanceData, EmployeeEntity.class);
    }

}
