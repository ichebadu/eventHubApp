package com.decagon.eventhubbe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private T data;
    private String message;


}
