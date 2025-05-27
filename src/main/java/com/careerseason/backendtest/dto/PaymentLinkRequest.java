package com.careerseason.backendtest.dto;

import lombok.Data;

@Data
public class PaymentLinkRequest {
    private String name;
    private String description;
    private long amount; // Amount in smallest currency unit (e.g., cents)
    private String currency = "usd"; // Default to USD
} 