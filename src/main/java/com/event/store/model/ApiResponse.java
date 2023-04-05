package com.event.store.model;

import lombok.Getter;

@Getter
public class ApiResponse {

    private String message;

    public ApiResponse(String message) {
        this.message = message;
    }
}
