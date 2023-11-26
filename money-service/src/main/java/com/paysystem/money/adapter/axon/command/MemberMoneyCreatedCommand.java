package com.paysystem.money.adapter.axon.command;

import com.paysystem.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberMoneyCreatedCommand extends SelfValidating<MemberMoneyCreatedCommand> {

    @NotNull
    private String membershipId;

    public MemberMoneyCreatedCommand(@NotNull String targetMembershipId) {
        this.membershipId = targetMembershipId;
        this.validateSelf();
    }

    public MemberMoneyCreatedCommand() {
    }
}
