package com.event.store.service;

import com.event.store.dao.AttendanceRepository;
import com.event.store.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EventStoreService {

    private final AttendanceRepository attendanceRepository;

    public EmployeeEntity getEmployeeAttendance(int id) {
        Optional<EmployeeEntity> employeeEntity = attendanceRepository.findById(id);
        return employeeEntity.isPresent() ? employeeEntity.get() : null;
    }

    public void saveAttendance(EmployeeEntity entity) {
        attendanceRepository.save(entity);
    }
}
