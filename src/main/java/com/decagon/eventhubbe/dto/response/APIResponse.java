package com.decagon.eventhubbe.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Data
@Builder
@JsonPropertyOrder(value = {"message", "time", "data"})
public class APIResponse<T> {
    private String message;
    private String time;
    private T data;

    public APIResponse() {
        this.message = "Processed Successfully";
        this.time = saveLocalDate(LocalDateTime.now());
    }

    public APIResponse(T data) {
        this.message = "Processed Successfully";
        this.time = saveLocalDate(LocalDateTime.now());
        this.data = data;
    }

    public APIResponse(String message, String time, T data) {
        this.message = message;
        this.time = time;
        this.data = data;
    }
    private String saveLocalDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss a");
        return date.format(formatter);
    }
    // Rest of the class...
}

