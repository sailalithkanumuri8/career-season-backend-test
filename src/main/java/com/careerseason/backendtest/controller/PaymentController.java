package com.careerseason.backendtest.controller;

import com.careerseason.backendtest.dto.PaymentLinkRequest;
import com.careerseason.backendtest.dto.PaymentLinkResponse;
import com.careerseason.backendtest.service.StripeService;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // For development; restrict in production
public class PaymentController {

    private final StripeService stripeService;

    @PostMapping("/create-payment-link")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestBody PaymentLinkRequest request) {
        try {
            String paymentUrl = stripeService.createPaymentLink(
                    request.getName(),
                    request.getDescription(),
                    request.getAmount(),
                    request.getCurrency()
            );
            return ResponseEntity.ok(PaymentLinkResponse.success(paymentUrl));
        } catch (StripeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(PaymentLinkResponse.error(e.getMessage()));
        }
    }
} 