package com.paysystem.remittance.application.port.inbound;

import com.paysystem.remittance.domain.RemittanceRequest;

import java.util.List;

public interface FindRemittanceUseCase {
    List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command);
}
