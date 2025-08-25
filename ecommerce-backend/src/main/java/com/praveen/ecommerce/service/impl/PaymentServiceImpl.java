package com.praveen.ecommerce.service.impl;
import com.praveen.ecommerce.dto.PaymentIntentRequestDto;
import com.praveen.ecommerce.dto.PaymentIntentResponseDto;
import com.praveen.ecommerce.service.IPaymentIntentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentIntentService {

    private static final long MIN_INR_AMOUNT = 5000L;

    @Override
    public PaymentIntentResponseDto createPaymentIntent(PaymentIntentRequestDto paymentIntentRequestDto) {
        try {
            if ("inr".equalsIgnoreCase(paymentIntentRequestDto.currency())
                    && paymentIntentRequestDto.amount() < MIN_INR_AMOUNT) {
                throw new IllegalArgumentException("Minimum payment amount is ₹50.00 (5000 paise).");
            }

            PaymentIntentCreateParams paymentIntentCreateParams = PaymentIntentCreateParams
                    .builder()
                    .setAmount(paymentIntentRequestDto.amount())
                    .setCurrency(paymentIntentRequestDto.currency())
                    .addPaymentMethodType("card")
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(paymentIntentCreateParams);
            return new PaymentIntentResponseDto(paymentIntent.getClientSecret());
        } catch (StripeException e) {
            throw new RuntimeException("Failed to create the payment intent: " + e.getMessage(), e);
        }
    }
}
