package com.decagon.eventhubbe.service;

import com.decagon.eventhubbe.dto.request.PaymentRequest;

public interface PaymentService {
    String payment(PaymentRequest paymentDTO);
}
