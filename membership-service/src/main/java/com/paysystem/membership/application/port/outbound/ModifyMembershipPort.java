package com.paysystem.membership.application.port.outbound;

import com.paysystem.membership.adapter.outbound.persistence.MembershipJpaEntity;
import com.paysystem.membership.domain.Membership;

public interface ModifyMembershipPort {
    MembershipJpaEntity modifyMembership(
        Membership.MembershipId membershipId,
        Membership.MembershipName membershipName,
        Membership.MembershipEmail membershipEmail,
        Membership.MembershipAddress membershipAddress,
        Membership.MembershipIsValid membershipIsValid,
        Membership.MembershipIsCorp membershipIsCorp
    );
}
