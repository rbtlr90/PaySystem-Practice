package com.paysystem.payment.adapter.inbound;

import com.paysystem.common.WebAdapter;
import com.paysystem.payment.application.port.inbound.RequestPaymentCommand;
import com.paysystem.payment.application.port.inbound.RequestPaymentUseCase;
import com.paysystem.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestPaymentController {
    private final RequestPaymentUseCase requestPaymentUseCase;
    @PostMapping(path = "/payment/request")
    Payment requestPayment(PaymentRequest request) {
        return requestPaymentUseCase.requestPayment(
                new RequestPaymentCommand(
                        request.getRequestMembershipId(),
                        request.getRequestPrice(),
                        request.getFranchiseId(),
                        request.getFranchiseFeeRate()
                )
        );
    }
}
