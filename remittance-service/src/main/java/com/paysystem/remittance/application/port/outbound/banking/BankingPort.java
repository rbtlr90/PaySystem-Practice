package com.paysystem.remittance.application.port.outbound.banking;

public interface BankingPort {

    BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber);

    boolean requestFirmbanking(String bankName, String bankAccountNumber, int amount);
}
