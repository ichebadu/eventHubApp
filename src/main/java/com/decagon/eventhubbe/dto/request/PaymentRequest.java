package com.decagon.eventhubbe.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String email;
    private BigDecimal amount;

}
