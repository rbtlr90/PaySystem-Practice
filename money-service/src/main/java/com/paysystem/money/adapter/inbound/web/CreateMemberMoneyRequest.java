package com.paysystem.money.adapter.inbound.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberMoneyRequest {
    private String membershipId;
}
