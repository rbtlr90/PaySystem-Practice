package com.paysystem.payment.application.port.outbound;

public interface GetMembershipPort {
    public MembershipStatus getMembership(String membershipId);
}
