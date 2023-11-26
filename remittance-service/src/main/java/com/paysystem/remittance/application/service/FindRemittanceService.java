package com.paysystem.remittance.application.service;

import com.paysystem.common.UseCase;
import com.paysystem.remittance.adapter.outbound.persistence.RemittanceRequestMapper;
import com.paysystem.remittance.application.port.inbound.FindRemittanceCommand;
import com.paysystem.remittance.application.port.inbound.FindRemittanceUseCase;
import com.paysystem.remittance.application.port.outbound.FindRemittancePort;
import com.paysystem.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional
public class FindRemittanceService implements FindRemittanceUseCase {
    private final FindRemittancePort findRemittancePort;
    private final RemittanceRequestMapper mapper;

    @Override
    public List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command) {
        //
        findRemittancePort.findRemittanceHistory(command);
        return null;
    }
}
