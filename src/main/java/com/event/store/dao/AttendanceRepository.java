package com.event.store.dao;

import com.event.store.entity.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends CrudRepository<EmployeeEntity, Integer> {
}
