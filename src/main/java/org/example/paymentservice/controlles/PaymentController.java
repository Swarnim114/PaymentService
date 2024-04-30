package org.example.paymentservice.controlles;

import com.stripe.exception.StripeException;
import org.example.paymentservice.dtos.InitiatePaymentRequestDto;
import org.example.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/make")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto) throws StripeException {
        return paymentService.initiatePayment(requestDto.getOrderId(), requestDto.getEmail());
    }
}
