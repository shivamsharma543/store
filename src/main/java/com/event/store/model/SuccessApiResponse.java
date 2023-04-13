package com.event.store.model;

import lombok.Getter;

@Getter
public class SuccessApiResponse extends ApiResponse {
    private int empId;
    private String empName;
    private String attendance;

    public SuccessApiResponse(int empId, String empName, String attendance, String message) {
        super(message);
        this.empId = empId;
        this.empName = empName;
        this.attendance = attendance;
    }
}
