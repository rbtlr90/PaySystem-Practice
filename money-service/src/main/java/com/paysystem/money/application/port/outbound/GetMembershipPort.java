package com.paysystem.money.application.port.outbound;

public interface GetMembershipPort {
    public MembershipStatus getMembership(String membershipId);
}
