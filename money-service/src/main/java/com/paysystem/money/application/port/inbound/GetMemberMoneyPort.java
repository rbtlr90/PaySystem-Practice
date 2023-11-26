package com.paysystem.money.application.port.inbound;

import com.paysystem.money.adapter.outbound.persistence.MemberMoneyJpaEntity;
import com.paysystem.money.domain.MemberMoney;

public interface GetMemberMoneyPort {
    MemberMoneyJpaEntity getMemberMoney(
            MemberMoney.MembershipId memberId
    );
}
