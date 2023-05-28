package com.decagon.eventhubbe.service.impl;

import com.decagon.eventhubbe.domain.entities.Event;
import com.decagon.eventhubbe.domain.entities.EventTicket;
import com.decagon.eventhubbe.domain.entities.Payment;
import com.decagon.eventhubbe.domain.repository.EventRepository;
import com.decagon.eventhubbe.domain.repository.EventTicketRepository;
import com.decagon.eventhubbe.domain.repository.PaymentRepository;
import com.decagon.eventhubbe.dto.request.PaymentRequest;
import com.decagon.eventhubbe.exception.EventNotFoundException;
import com.decagon.eventhubbe.exception.UnauthorizedException;
import com.decagon.eventhubbe.service.PaymentService;
import com.decagon.eventhubbe.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final EventRepository eventRepository;
    private final EventTicketRepository eventTicketRepository;


    @Override
    public String makePayment(PaymentRequest paymentRequest, String eventId, String ticketId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(()-> new EventNotFoundException(eventId));
        EventTicket eventTicket = eventTicketRepository.findById(ticketId)
                .orElseThrow(()-> new RuntimeException("Ticket Not found"));
        paymentRepository.save(Payment.builder()
                        .event(event)
                        .eventTicket(eventTicket)
                        .buyerName(paymentRequest.getBuyerName())
                        .buyerEmail(paymentRequest.getBuyerEmail())
                        .qty(paymentRequest.getQty())
                        .purchaseDate(DateUtils.saveDate(LocalDateTime.now()))
                        .amount(BigDecimal.valueOf(paymentRequest.getQty() * eventTicket.getTicketPrice()))
                .build());
        return "Payment Made Successfully";
    }
}
