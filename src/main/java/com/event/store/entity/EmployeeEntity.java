package com.event.store.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Employee")
@Getter
public class EmployeeEntity {

    @Id
    private int empId;
    private String name;
    private int attendance;
}
