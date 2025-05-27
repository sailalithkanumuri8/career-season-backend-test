package com.careerseason.backendtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentLinkResponse {
    private String paymentUrl;
    private boolean success;
    private String message;

    public static PaymentLinkResponse success(String paymentUrl) {
        return new PaymentLinkResponse(paymentUrl, true, "Payment link created successfully");
    }

    public static PaymentLinkResponse error(String errorMessage) {
        return new PaymentLinkResponse(null, false, errorMessage);
    }
} 