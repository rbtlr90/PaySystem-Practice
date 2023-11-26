package com.paysystem.remittance.adapter.outbound.service.banking;

import com.paysystem.common.CommonHttpClient;
import com.paysystem.common.ExternalSystemAdapter;
import com.paysystem.remittance.application.port.outbound.banking.BankingInfo;
import com.paysystem.remittance.application.port.outbound.banking.BankingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankingServiceAdapter implements BankingPort {

    private final CommonHttpClient bankingServiceHttpClient;

    @Value("${service.banking.url}")
    private String bankingServiceEndpoint;


    @Override
    public BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber) {

        return null;
    }

    @Override
    public boolean requestFirmbanking(String bankName, String bankAccountNumber, int amount) {
        return false;
    }
}
