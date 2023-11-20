package com.paysystem.membership.application.port.inbound;

import com.paysystem.membership.domain.Membership;

public interface FindMembershipUseCase {
    Membership findMembership(FindMembershipCommand command);
}
