package com.paysystem.remittance.application.port.inbound;

import com.paysystem.remittance.domain.RemittanceRequest;

public interface RequestRemittanceUseCase {
    RemittanceRequest requestRemittance(RequestRemittanceCommand command);
}
