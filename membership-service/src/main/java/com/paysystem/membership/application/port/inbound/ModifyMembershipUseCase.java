package com.paysystem.membership.application.port.inbound;

import com.paysystem.membership.domain.Membership;

public interface ModifyMembershipUseCase {
    Membership modifyMembership(ModifyMembershipCommand command);
}
