package dev.velasquez.javatest.payments;

public interface PaymentGateway {

    PaymentResponse requestPayment(PaymentRequest paymentRequest);

}
