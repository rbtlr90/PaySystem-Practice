package com.paysystem.remittance.application.port.outbound.money;

import lombok.Data;

@Data
public class MoneyInfo {

    private String membershipId;
    private int balance;
}
