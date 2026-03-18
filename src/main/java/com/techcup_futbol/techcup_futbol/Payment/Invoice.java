package com.techcup_futbol.techcup_futbol.Payment;

import java.sql.Date;

import com.techcup_futbol.techcup_futbol.Tournament.Tournament;

public class Invoice {

    private Long id;
    private double amount;
    private PaymentStatus status;
    private Payment payment;
    private Date date;
    private Tournament tournament; 

    public PaymentStatus getState() {
        return null;
    }

    public double getAmount() {
        return 0;
    }

    public Date getDate() {
        return null;
    }
}