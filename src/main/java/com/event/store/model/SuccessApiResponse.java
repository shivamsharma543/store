package com.event.store.model;

import lombok.Getter;

@Getter
public class SuccessApiResponse extends ApiResponse {
    private int empId;
    private String empName;
    private int attendance;

    public SuccessApiResponse(int empId, String empName, int attendance, String message) {
        super(message);
        this.empId = empId;
        this.empName = empName;
        this.attendance = attendance;
    }
}
