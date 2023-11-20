package com.paysystem.membership.application.port.outbound;

import com.paysystem.membership.adapter.outbound.persistence.MembershipJpaEntity;
import com.paysystem.membership.domain.Membership;

public interface FindMembershipPort {
    MembershipJpaEntity findMembership(
            Membership.MembershipId membershipId
    );
}
