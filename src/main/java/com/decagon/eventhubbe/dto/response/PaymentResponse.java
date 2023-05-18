package com.decagon.eventhubbe.dto.response;

import lombok.Data;

@Data
public class  PaymentResponse<T> {
    private T data;
    private String message;
}
