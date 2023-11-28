package com.paysystem.payment.application.port.outbound;

import com.paysystem.payment.domain.Payment;

public interface CreatePaymentPort {
    Payment createPayment(String requestMembershipId, String requestPrice, String franchiseId, String franchiseFeeRate);
}
