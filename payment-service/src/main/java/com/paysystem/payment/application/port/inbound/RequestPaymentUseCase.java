package com.paysystem.payment.application.port.inbound;

import com.paysystem.payment.domain.Payment;

public interface RequestPaymentUseCase {
    Payment requestPayment(RequestPaymentCommand command);
}
