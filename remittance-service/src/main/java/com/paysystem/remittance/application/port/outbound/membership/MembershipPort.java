package com.paysystem.remittance.application.port.outbound.membership;

public interface MembershipPort {

    MembershipStatus getMembershipStatus(String membershipId);
}
