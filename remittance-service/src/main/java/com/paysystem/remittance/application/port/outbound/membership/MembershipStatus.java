package com.paysystem.remittance.application.port.outbound.membership;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipStatus {
    private String membershipId;
    private boolean isValid;
}
