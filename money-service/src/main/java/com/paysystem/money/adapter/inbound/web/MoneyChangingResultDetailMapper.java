package com.paysystem.money.adapter.inbound.web;

import com.paysystem.money.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyChangingResultDetailMapper {
    public MoneyChangingResultDetail mapToMoneyChangingResultDetail(MoneyChangingRequest moneyChangingRequest) {
//        return new MoneyChangingResultDetail(
//                moneyChangingRequest.getMoneyChangingRequestId(),
//                moneyChangingRequest.getChangingType(),
//                moneyChangingRequest.getChangingMoneyStatus(),
//                moneyChangingRequest.getChangingMoneyAmount()
//        );
        return null;
    }
}
