package com.event.store.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KafkaPayload {

    private int empId;
    private String name;
    private double attendance;

}
