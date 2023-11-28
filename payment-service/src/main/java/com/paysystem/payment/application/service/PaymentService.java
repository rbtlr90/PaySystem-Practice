package com.paysystem.payment.application.service;

import com.paysystem.common.UseCase;
import com.paysystem.payment.application.port.inbound.RequestPaymentCommand;
import com.paysystem.payment.application.port.inbound.RequestPaymentUseCase;
import com.paysystem.payment.application.port.outbound.CreatePaymentPort;
import com.paysystem.payment.application.port.outbound.GetMembershipPort;
import com.paysystem.payment.application.port.outbound.GetRegisteredBankAccountPort;
import com.paysystem.payment.domain.Payment;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class PaymentService implements RequestPaymentUseCase {

    private final CreatePaymentPort createPaymentPort;

    private final GetMembershipPort getMembershipPort;
    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;

    // Todo Money Service -> Member Money 정보를 가져오기 위한 Port

    @Override
    public Payment requestPayment(RequestPaymentCommand command) {

        // 충전도, 멤버십, 머니 유효성 확인.....
        // getMembershipPort.getMembership(command.getRequestMembershipId());

        //getRegisteredBankAccountPort.getRegisteredBankAccount(command.getRequestMembershipId());

        //....

        // createPaymentPort
        return createPaymentPort.createPayment(
                command.getRequestMembershipId(),
                command.getRequestPrice(),
                command.getFranchiseId(),
                command.getFranchiseFeeRate());
    }
}
