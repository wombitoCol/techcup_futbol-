package com.techcup_futbol.techcup_futbol.Payment;

import java.util.Date;

public interface Payment {

    PaymentStatus getState();

    double getAmount();

    Date getDate();
}