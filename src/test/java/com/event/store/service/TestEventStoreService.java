package com.event.store.service;

import com.event.store.dao.AttendanceRepository;
import com.event.store.entity.EmployeeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class TestEventStoreService {

    @InjectMocks
    EventStoreService eventStoreService;
    @Mock
    private AttendanceRepository attendanceRepository;
    Optional<EmployeeEntity> employeeEntity;

    @BeforeEach
    void setUp(){
        openMocks(this);
        employeeEntity = Optional.of(EmployeeEntity.builder()
                                                   .attendance("Present")
                                                   .name("shiva")
                                                   .empId(43301)
                                                   .build());
    }

    @Test
    void testGetEmployeeAttendance(){
        when(attendanceRepository.findById(43301)).thenReturn(employeeEntity);
        EmployeeEntity actualEmployeeEntity = eventStoreService.getEmployeeAttendance(43301);
        assertEquals(employeeEntity.get(),actualEmployeeEntity);
    }

    @Test
    void testSaveAttendance(){
        when(attendanceRepository.save(any(EmployeeEntity.class))).thenReturn(employeeEntity.get());
        eventStoreService.saveAttendance(employeeEntity.get());
        verify(attendanceRepository,times(1)).save(any(EmployeeEntity.class));

    }

}
