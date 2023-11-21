package com.paysystem.money.adapter.inbound.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyChangingResultDetail {
    private String moneyChangingRequestId;

    // 증액, 감액
    private int moneyChangingType; // 0: 증액, 1: 감액
//    private MoneyChangingType moneyChangingType; // 0: 증액, 1: 감액

//    private MoneyChangingResultStatus moneyChangingResultStatus;
    private int moneyChangingResultStatus; // 0: 성공, 실패, 실패 - 잔액 부족, 실패 - 멤버십 없음, 실패 - 머니 변액 요청 없음
    private int amount;

}

//enum MoneyChangingType {
//    INCREASING,
//    DECREASING
//}
//
//enum MoneyChangingResultStatus {
//    SUCCEEDED,
//    FAILED,
//    FAILED_NOT_ENOUGH_MONEY, // 잔액부족
//    FAILED_NOT_EXIST_MEMBERSHIP, // 멤버쉽 없음
//    FAILED_NOT_EXIST_MONEY_CHANGING_REQUEST // 머니 변액 요청 없음
//}