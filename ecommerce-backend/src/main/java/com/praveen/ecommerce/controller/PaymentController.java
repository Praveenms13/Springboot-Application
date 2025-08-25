package com.praveen.ecommerce.controller;

import com.praveen.ecommerce.dto.PaymentIntentRequestDto;
import com.praveen.ecommerce.dto.PaymentIntentResponseDto;
import com.praveen.ecommerce.service.IPaymentIntentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final IPaymentIntentService ipaymentIntentService;

    @PostMapping("/create-payment-intent")
    public ResponseEntity<PaymentIntentResponseDto> createPaymentIntent(@RequestBody PaymentIntentRequestDto paymentIntentRequestDto) {
        PaymentIntentResponseDto paymentIntentResponseDto = ipaymentIntentService.createPaymentIntent(paymentIntentRequestDto);
        return ResponseEntity.ok(paymentIntentResponseDto);
    }
}
