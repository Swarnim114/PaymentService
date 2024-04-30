package org.example.paymentservice.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import org.example.paymentservice.paymentGateways.PaymentGateway;
import org.example.paymentservice.paymentGateways.StripePaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
        private StripePaymentGateway paymentGateway;
        PaymentService(StripePaymentGateway paymentGateway) {this.paymentGateway = paymentGateway;}

        public String initiatePayment(Long orderId, String email) throws StripeException {
                return paymentGateway.generatePaymentLink(orderId, email);
        }
}
