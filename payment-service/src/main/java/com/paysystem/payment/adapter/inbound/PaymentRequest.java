package com.paysystem.payment.adapter.inbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private String requestMembershipId;
    private String requestPrice;
    private String franchiseId;
    private String franchiseFeeRate;
}
