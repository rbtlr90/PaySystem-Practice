package com.paysystem.banking.adapter.inbound.web;

import com.paysystem.banking.application.port.inbound.RegisterBankAccountCommand;
import com.paysystem.banking.application.port.inbound.RegisterBankAccountUseCase;
import com.paysystem.banking.domain.RegisteredBankAccount;
import com.paysystem.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping(path = "/banking/account/register")
    RegisteredBankAccount registerMembership(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .isValid(request.isValid())
                .build();

        return registerBankAccountUseCase.registerBankAccount(command);
    }

}
