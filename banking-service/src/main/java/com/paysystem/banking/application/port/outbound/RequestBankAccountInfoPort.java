package com.paysystem.banking.application.port.outbound;

import com.paysystem.banking.adapter.outbound.external.bank.BankAccount;
import com.paysystem.banking.adapter.outbound.external.bank.GetBankAccountRequest;

public interface RequestBankAccountInfoPort {
    BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
