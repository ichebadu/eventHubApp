package com.decagon.eventhubbe.controller;

import com.decagon.eventhubbe.dto.request.PaymentRequest;
import com.decagon.eventhubbe.dto.response.APIResponse;
import com.decagon.eventhubbe.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/payment")
@Tag(
        name = "Payment API"
)
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(
            summary = "Make Payment",
            description = "Initiates the payment process for an event ticket."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED"
    )
    @PostMapping("/event/{eventId}/ticket")
    @CrossOrigin(origins = "http://localhost:5173")
    public ResponseEntity<APIResponse<String>> makePayment(@RequestBody PaymentRequest paymentRequest, @PathVariable String eventId){
        APIResponse<String> apiResponse = new APIResponse<>(paymentService.makePayment(paymentRequest,eventId));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
