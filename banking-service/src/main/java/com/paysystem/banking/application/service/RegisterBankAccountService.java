package com.paysystem.banking.application.service;

import com.paysystem.banking.adapter.outbound.external.bank.BankAccount;
import com.paysystem.banking.adapter.outbound.external.bank.GetBankAccountRequest;
import com.paysystem.banking.adapter.outbound.persistence.RegisteredBankAccountJpaEntity;
import com.paysystem.banking.adapter.outbound.persistence.RegisteredBankAccountMapper;
import com.paysystem.banking.application.port.inbound.RegisterBankAccountCommand;
import com.paysystem.banking.application.port.inbound.RegisterBankAccountUseCase;
import com.paysystem.banking.application.port.outbound.GetMembershipPort;
import com.paysystem.banking.application.port.outbound.MembershipStatus;
import com.paysystem.banking.application.port.outbound.RegisterBankAccountPort;
import com.paysystem.banking.application.port.outbound.RequestBankAccountInfoPort;
import com.paysystem.banking.domain.RegisteredBankAccount;
import com.paysystem.common.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {
    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper mapper;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;
    private final GetMembershipPort getMembershipPort;
    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {

        // 은행 계좌를 등록해야하는 서비스 (비즈니스 로직)
        // command.getMembershipId()

        // call membership svc, 정상인지 확인
        // call external bank svc, 정상인지 확인
        MembershipStatus membershipStatus = getMembershipPort.getMembership(command.getMembershipId());
        if(!membershipStatus.isValid()) {
            return null;
        }

        // 1. 외부 실제 은행에 등록이 가능한 계좌인지(정상인지) 확인한다.
        // 외부의 은행에 이 계좌 정상인지? 확인을 해야해요.
        // Biz Logic -> External System
        // Port -> Adapter -> External System
        // Port
        // 실제 외부의 은행계좌 정보를 Get
        BankAccount accountInfo = requestBankAccountInfoPort.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        boolean accountIsValid =  accountInfo.isValid();

        // 2. 등록가능한 계좌라면, 등록한다. 성공하면, 등록에 성공한 등록 정보를 리턴
        // 2-1. 등록가능하지 않은 계좌라면. 에러를 리턴
        if(accountIsValid) {
            // 등록 정보 저장
            RegisteredBankAccountJpaEntity savedAccountInfo = registerBankAccountPort.createRegisteredBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId()+""),
                    new RegisteredBankAccount.BankName(command.getBankName()),
                    new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new RegisteredBankAccount.LinkedStatusIsValid(command.isValid())
            );
            return mapper.mapToDomainEntity(savedAccountInfo);
        } else {
            return null;
        }
    }

}
