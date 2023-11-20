package com.paysystem.membership.application.service;

import com.paysystem.common.UseCase;
import com.paysystem.membership.adapter.outbound.persistence.MembershipJpaEntity;
import com.paysystem.membership.adapter.outbound.persistence.MembershipMapper;
import com.paysystem.membership.application.port.inbound.ModifyMembershipCommand;
import com.paysystem.membership.application.port.inbound.ModifyMembershipUseCase;
import com.paysystem.membership.application.port.outbound.ModifyMembershipPort;
import com.paysystem.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifyMembershipService implements ModifyMembershipUseCase {
    private final ModifyMembershipPort modifyMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipJpaEntity membershipJpaEntity = modifyMembershipPort.modifyMembership(
                new Membership.MembershipId(command.getMembershipId()),
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );
        return membershipMapper.mapToDomainEntity(membershipJpaEntity);
    }

}
