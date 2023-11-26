package com.paysystem.remittance.application.port.outbound;

import com.paysystem.remittance.adapter.outbound.persistence.RemittanceRequestJpaEntity;
import com.paysystem.remittance.application.port.inbound.RequestRemittanceCommand;

public interface RequestRemittancePort {

    RemittanceRequestJpaEntity createRemittanceRequestHistory(RequestRemittanceCommand command);
    boolean saveRemittanceRequestHistory(RemittanceRequestJpaEntity entity);
}
