package com.app.Recap.utilities;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class StatusResponse {

    private String status;
    private LocalDateTime timeStamp;

    public StatusResponse(String status){
        this.status = status;
        this.timeStamp = LocalDateTime.now();
    }


}
