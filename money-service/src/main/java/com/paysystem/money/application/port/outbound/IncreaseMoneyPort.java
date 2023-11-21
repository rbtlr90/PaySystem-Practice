package com.paysystem.money.application.port.outbound;

import com.paysystem.money.adapter.outbound.persistence.MemberMoneyJpaEntity;
import com.paysystem.money.adapter.outbound.persistence.MoneyChangingRequestJpaEntity;
import com.paysystem.money.domain.MemberMoney;
import com.paysystem.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyPort {

    MoneyChangingRequestJpaEntity createMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.MoneyChangingType moneyChangingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus,
            MoneyChangingRequest.Uuid uuid
    );

    MemberMoneyJpaEntity increaseMoney(
            MemberMoney.MembershipId memberId,
            int increaseMoneyAmount
    );
}
