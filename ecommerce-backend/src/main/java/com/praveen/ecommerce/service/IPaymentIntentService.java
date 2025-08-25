package com.praveen.ecommerce.service;

import com.praveen.ecommerce.dto.PaymentIntentRequestDto;
import com.praveen.ecommerce.dto.PaymentIntentResponseDto;

public interface IPaymentIntentService {
    PaymentIntentResponseDto createPaymentIntent(PaymentIntentRequestDto paymentIntentRequestDto);
}
