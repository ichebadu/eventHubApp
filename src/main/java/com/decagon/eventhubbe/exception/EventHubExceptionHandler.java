package com.decagon.eventhubbe.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class EventHubExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EventHubExceptionResponse> userNotFound(AppUserNotFoundException e,
                                                                 HttpServletRequest request){
        EventHubExceptionResponse exceptionResponse = EventHubExceptionResponse.builder()
                .time(LocalDateTime.now())
                .message(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }
}
