package com.paysystem.banking.application.port.inbound;

public interface UpdateFirmbankingUseCase {
    void updateFirmbankingByEvent(UpdateFirmbankingCommand command);
}
