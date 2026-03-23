/* 
package com.techcup_futbol.techcup_futbol.testPayment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.techcup_futbol.techcup_futbol.Payment.Invoice;
import com.techcup_futbol.techcup_futbol.Payment.Payment;
import com.techcup_futbol.techcup_futbol.Payment.PaymentStatus;

import java.util.Date;

public class PaymentIntegrationTest {

    @Test
    void shouldCreateInvoiceWithoutErrors() {
        Invoice invoice = new Invoice();
        assertNotNull(invoice);
    }

    @Test
    void shouldHaveNullPaymentInitially() {
        Invoice invoice = new Invoice();
        assertNull(invoice.getState());
    }

    @Test
    void shouldReturnDefaultAmount() {
        Invoice invoice = new Invoice();
        assertEquals(0, invoice.getAmount());
    }

    @Test
    void shouldReturnNullDate() {
        Invoice invoice = new Invoice();
        assertNull(invoice.getDate());
    }

    @Test
    void shouldHandlePaymentInterfaceImplementation() {
        Payment payment = new Payment() {
            @Override
            public PaymentStatus getState() { return PaymentStatus.PENDING; }

            @Override
            public double getAmount() { return 100; }

            @Override
            public Date getDate() { return new Date(); }
        };

        assertEquals(PaymentStatus.PENDING, payment.getState());
    }
}
*/