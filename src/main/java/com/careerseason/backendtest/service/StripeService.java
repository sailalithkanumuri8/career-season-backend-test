package com.careerseason.backendtest.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    public String createPaymentLink(String name, String description, long amount, String currency) throws StripeException {
        // 1. Create a Product
        ProductCreateParams productParams = ProductCreateParams.builder()
                .setName(name)
                .setDescription(description)
                .build();
        Product product = Product.create(productParams);

        // 2. Create a Price (one-time)
        PriceCreateParams priceParams = PriceCreateParams.builder()
                .setProduct(product.getId())
                .setCurrency(currency)
                .setUnitAmount(amount) // Amount in smallest currency unit (e.g., cents)
                .build();
        Price price = Price.create(priceParams);

        // 3. Create a Payment Link
        PaymentLinkCreateParams paymentLinkParams = PaymentLinkCreateParams.builder()
                .addLineItem(
                        PaymentLinkCreateParams.LineItem.builder()
                                .setPrice(price.getId())
                                .setQuantity(1L)
                                .build()
                )
                .build();
        PaymentLink paymentLink = PaymentLink.create(paymentLinkParams);

        // Return the URL that customers will visit to pay
        return paymentLink.getUrl();
    }
}
