package com.decagon.eventhubbe.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class EventHubExceptionResponse {
    private LocalDateTime time;
    private String message;
    private String path;
    private Integer statusCode;
}
