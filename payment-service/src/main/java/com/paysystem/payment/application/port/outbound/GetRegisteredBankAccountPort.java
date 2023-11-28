package com.paysystem.payment.application.port.outbound;


public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountAggregateIdentifier getRegisteredBankAccount(String membershipId);
}
