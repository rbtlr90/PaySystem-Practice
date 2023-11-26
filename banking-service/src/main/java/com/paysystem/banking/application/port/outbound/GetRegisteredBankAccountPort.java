package com.paysystem.banking.application.port.outbound;

import com.paysystem.banking.adapter.outbound.persistence.RegisteredBankAccountJpaEntity;
import com.paysystem.banking.application.port.inbound.GetRegisteredBankAccountCommand;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountJpaEntity getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
