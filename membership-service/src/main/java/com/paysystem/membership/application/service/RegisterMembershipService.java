package com.paysystem.membership.application.service;

import com.paysystem.common.UseCase;
import com.paysystem.membership.adapter.outbound.persistence.MembershipJpaEntity;
import com.paysystem.membership.adapter.outbound.persistence.MembershipMapper;
import com.paysystem.membership.application.port.inbound.RegisterMembershipCommand;
import com.paysystem.membership.application.port.inbound.RegisterMembershipUseCase;
import com.paysystem.membership.application.port.outbound.RegisterMembershipPort;
import com.paysystem.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterMembershipService implements RegisterMembershipUseCase {
    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {

        MembershipJpaEntity membershipJpaEntity = registerMembershipPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );

        return membershipMapper.mapToDomainEntity(membershipJpaEntity);
    }

}
