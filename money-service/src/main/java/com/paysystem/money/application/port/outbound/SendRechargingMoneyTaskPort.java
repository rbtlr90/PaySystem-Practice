package com.paysystem.money.application.port.outbound;

import com.paysystem.common.RechargingMoneyTask;

public interface SendRechargingMoneyTaskPort {
    void sendRechargingMoneyTaskPort(
            RechargingMoneyTask task
    );
}
