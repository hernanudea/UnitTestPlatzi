package dev.velasquez.javatest.payments;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PaymentProcessorTest {

    PaymentGateway paymentGateway;
    PaymentProcessor paymentProcessor;

    // Este metodo se ejecuta antes de ejecutar cada uno de los tests
    @Before
    public void setup() {
        paymentGateway = Mockito.mock(PaymentGateway.class);
        paymentProcessor = new PaymentProcessor(paymentGateway);
    }

    @Test
    public void paymentIsCorrect() {
        // preparación de los objetos
        Mockito.when(paymentGateway.requestPayment(Mockito.any()))
                .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.OK));

        // ejecución del metodo
        boolean result = paymentProcessor.makePayment(1000);

        // comprobación del resultado
        assertTrue(result);
    }

    @Test
    public void paymentIsWrong() {
        Mockito.when(paymentGateway.requestPayment(Mockito.any()))
                .thenReturn(new PaymentResponse(PaymentResponse.PaymentStatus.ERROR));

        boolean result = paymentProcessor.makePayment(1000);
        assertFalse(result);
    }
}