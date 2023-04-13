package com.event.store.service;

import com.event.store.entity.EmployeeEntity;
import com.event.store.model.KafkaPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.event.store.model.AttendanceStatus.*;

@Service
@AllArgsConstructor
@Slf4j
public class AttendanceConsumer {

    private static final String topic = "attendanceTopic";
    private final ObjectMapper objectMapper;
    private final EventStoreService eventStoreService;

    @KafkaListener(topics = topic,
            groupId = "attendanceGroup1")
    public void attendanceConsumer(String attendanceData) {
        try {
            KafkaPayload kafkaPayload = convertStringToEmployee(attendanceData);
            eventStoreService.saveAttendance(mapKafkaPayloadToEmployeeEntity(kafkaPayload));
        } catch (JsonProcessingException ex) {
            log.error("exception {}:",ex.getMessage());
            log.error("error to read attendance data: {} from topic {}", topic);
        }
    }

    private KafkaPayload convertStringToEmployee(String attendanceData) throws JsonProcessingException {
        return objectMapper.readValue(attendanceData, KafkaPayload.class);
    }

    private EmployeeEntity mapKafkaPayloadToEmployeeEntity(KafkaPayload kafkaPayload){
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .presentHours(kafkaPayload.getAttendance())
                .empId(kafkaPayload.getEmpId())
                .name(kafkaPayload.getName())
                .attendance(calculateAttendance(kafkaPayload.getAttendance()))
                .build();

        return employeeEntity;
    }

    private String calculateAttendance(double hours){
        if(hours>0){
            if(hours <= 4){
                return HALF_DAY.name();
            } else if (hours>4 && hours<8) {
                return HALF_DAY.name();
            }else return PRESENT.name();
        }else {
            return ABSENT.name();
        }
    }
}
