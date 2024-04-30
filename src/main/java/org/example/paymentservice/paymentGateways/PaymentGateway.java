package org.example.paymentservice.paymentGateways;

import com.stripe.exception.StripeException;
import org.springframework.stereotype.Component;

@Component
public interface PaymentGateway {
    public String generatePaymentLink(Long orderId, String email) throws StripeException;
}

