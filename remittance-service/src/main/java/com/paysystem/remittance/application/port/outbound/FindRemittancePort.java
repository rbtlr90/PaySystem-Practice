package com.paysystem.remittance.application.port.outbound;

import com.paysystem.remittance.adapter.outbound.persistence.RemittanceRequestJpaEntity;
import com.paysystem.remittance.application.port.inbound.FindRemittanceCommand;

import java.util.List;

public interface FindRemittancePort {

    List<RemittanceRequestJpaEntity> findRemittanceHistory(FindRemittanceCommand command);
}
