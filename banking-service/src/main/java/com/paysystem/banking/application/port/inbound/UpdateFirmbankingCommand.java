package com.paysystem.banking.application.port.inbound;

import com.paysystem.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateFirmbankingCommand extends SelfValidating<RequestFirmbankingCommand> {
    @NotNull
    private final String firmbankingAggregateIdentifier;

    @NotNull
    private final int firmbankingStatus;

}
