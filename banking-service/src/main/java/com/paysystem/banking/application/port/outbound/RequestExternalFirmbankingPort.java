package com.paysystem.banking.application.port.outbound;

import com.paysystem.banking.adapter.outbound.external.bank.ExternalFirmbankingRequest;
import com.paysystem.banking.adapter.outbound.external.bank.FirmbankingResult;

public interface RequestExternalFirmbankingPort {
    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request);
}
