package com.kd.mongo.mongo_sample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private String errorMessage;
    private HttpStatus status;
    private LocalDateTime timeStamp;
    private String apiPath;
}
