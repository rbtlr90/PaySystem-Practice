package com.paysystem.money.application.port.inbound;

import com.paysystem.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyRequestUseCase {
    MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command);
    MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command);
    void increaseMoneyRequestByEvent(IncreaseMoneyRequestCommand command);
}
