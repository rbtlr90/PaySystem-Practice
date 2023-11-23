package com.paysystem.banking.application.port.outbound;

public interface GetMembershipPort {
    public MembershipStatus getMembership(String membershipId);
}
