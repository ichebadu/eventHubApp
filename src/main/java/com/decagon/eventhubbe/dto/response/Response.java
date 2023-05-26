package com.decagon.eventhubbe.dto.response;

import com.decagon.eventhubbe.dto.SpitPayementResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private SpitPayementResponse data;
    private String message;
    private boolean status;


}
