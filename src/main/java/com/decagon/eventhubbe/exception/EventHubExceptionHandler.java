package com.decagon.eventhubbe.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class EventHubExceptionHandler {
    @ExceptionHandler(AppUserNotFoundException.class)
    public ResponseEntity<EventHubExceptionResponse> userNotFound(AppUserNotFoundException e,
                                                                 HttpServletRequest request){
        EventHubExceptionResponse exceptionResponse = EventHubExceptionResponse.builder()
                .time(saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(AppUserAlreadyExistException.class)
    public ResponseEntity<EventHubExceptionResponse> userAlreadyExist(AppUserAlreadyExistException e,
                                                                  HttpServletRequest request){
        EventHubExceptionResponse exceptionResponse = EventHubExceptionResponse.builder()
                .time(saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
    private String saveLocalDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss a");
        return date.format(formatter);
    }
}
