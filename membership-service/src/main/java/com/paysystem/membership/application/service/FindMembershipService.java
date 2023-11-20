package com.paysystem.membership.application.service;

import com.paysystem.common.UseCase;
import com.paysystem.membership.adapter.outbound.persistence.MembershipJpaEntity;
import com.paysystem.membership.adapter.outbound.persistence.MembershipMapper;
import com.paysystem.membership.application.port.inbound.FindMembershipCommand;
import com.paysystem.membership.application.port.inbound.FindMembershipUseCase;
import com.paysystem.membership.application.port.outbound.FindMembershipPort;
import com.paysystem.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class FindMembershipService implements FindMembershipUseCase {
    private final FindMembershipPort findMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipJpaEntity membershipJpaEntity = findMembershipPort.findMembership(new Membership.MembershipId(command.getMembershipId()));
        return membershipMapper.mapToDomainEntity(membershipJpaEntity);
    }
}
