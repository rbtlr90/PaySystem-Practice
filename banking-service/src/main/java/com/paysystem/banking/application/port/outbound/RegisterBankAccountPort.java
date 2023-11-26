package com.paysystem.banking.application.port.outbound;

import com.paysystem.banking.adapter.outbound.persistence.RegisteredBankAccountJpaEntity;
import com.paysystem.banking.domain.RegisteredBankAccount;


public interface RegisterBankAccountPort {
    RegisteredBankAccountJpaEntity createRegisteredBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid,
            RegisteredBankAccount.AggregateIdentifier aggregateIdentifier
    );
}
