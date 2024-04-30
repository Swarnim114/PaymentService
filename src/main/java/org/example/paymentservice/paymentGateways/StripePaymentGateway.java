package org.example.paymentservice.paymentGateways;

import org.springframework.stereotype.Component;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StripePaymentGateway implements PaymentGateway {

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @Override
    public String generatePaymentLink(Long orderId, String email) throws StripeException {
        Stripe.apiKey = "sk_test_51PBJguSIyYupbN0ApZKOa7LTU0hHuoLGjCiXCVetw1jDbRSPLkJ8eITV8MKRQwYk80tz6SXL4ktTaoTo1qOD4Lnb00P7uAghaM";

        SessionCreateParams.Builder builder = new SessionCreateParams.Builder();
        builder.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD);
        builder.setMode(SessionCreateParams.Mode.PAYMENT);
        builder.setSuccessUrl("https://www.moneytype.com/");
        builder.setCancelUrl("https://example.com/cancel");
        builder.addLineItem(
                SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("inr")
                                        .setUnitAmount(1000L)
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName("Sample payment link for  me")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build()
        );

        Session session = Session.create(builder.build());
        return session.getUrl();
    }
}

