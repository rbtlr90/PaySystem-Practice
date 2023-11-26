package com.paysystem.banking.application.service;

import com.paysystem.banking.adapter.axon.command.CreateFirmbankingRequestCommand;
import com.paysystem.banking.adapter.axon.command.UpdateFirmbankingRequestCommand;
import com.paysystem.banking.adapter.outbound.external.bank.ExternalFirmbankingRequest;
import com.paysystem.banking.adapter.outbound.external.bank.FirmbankingResult;
import com.paysystem.banking.adapter.outbound.persistence.FirmbankingRequestJpaEntity;
import com.paysystem.banking.adapter.outbound.persistence.FirmbankingRequestMapper;
import com.paysystem.banking.application.port.inbound.RequestFirmbankingCommand;
import com.paysystem.banking.application.port.inbound.RequestFirmbankingUseCase;
import com.paysystem.banking.application.port.inbound.UpdateFirmbankingCommand;
import com.paysystem.banking.application.port.inbound.UpdateFirmbankingUseCase;
import com.paysystem.banking.application.port.outbound.RequestExternalFirmbankingPort;
import com.paysystem.banking.application.port.outbound.RequestFirmbankingPort;
import com.paysystem.banking.domain.FirmbankingRequest;
import com.paysystem.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

import javax.transaction.Transactional;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmbankingService implements RequestFirmbankingUseCase, UpdateFirmbankingUseCase {
    private final FirmbankingRequestMapper mapper;
    private final RequestFirmbankingPort requestFirmbankingPort;

    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;
    private final CommandGateway commandGateway;

    @Override
    public FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command) {

        // Business Logic
        // a -> b 계좌

        // 1. 요청에 대해 정보를 먼저 write . "요청" 상태로
        FirmbankingRequestJpaEntity requestedEntity = requestFirmbankingPort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(0),
                new FirmbankingRequest.FirmbankingAggregateIdentifier("")
        );

        // 2. 외부 은행에 펌뱅킹 요청
        FirmbankingResult result = requestExternalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount()
        ));

        // Transactional UUID
        UUID randomUUID = UUID.randomUUID();
        requestedEntity.setUuid(randomUUID.toString());

        // 3. 결과에 따라서 1번에서 작성했던 FirmbankingRequest 정보를 Update
        if (result.getResultCode() == 0){
            // 성공
            requestedEntity.setFirmbankingStatus(1);
        } else {
            // 실패
            requestedEntity.setFirmbankingStatus(2);
        }

        // 4. 결과를 리턴하기 전에 바뀐 상태 값을 기준으로 다시 save
        return mapper.mapToDomainEntity(requestFirmbankingPort.modifyFirmbankingRequest(requestedEntity), randomUUID);
    }

    @Override
    public void requestFirmbankingByEvent(RequestFirmbankingCommand command) {
        CreateFirmbankingRequestCommand createFirmbankingRequestCommand = CreateFirmbankingRequestCommand.builder()
                .toBankName(command.getToBankName())
                .toBankAccountNumber(command.getToBankAccountNumber())
                .fromBankName(command.getFromBankName())
                .fromBankAccountNumber(command.getFromBankAccountNumber())
                .moneyAmount(command.getMoneyAmount())
                .build();

        commandGateway.send(createFirmbankingRequestCommand).whenComplete(
                (result, throwable) -> {
                    if (throwable != null){
                        // 실패
                        throwable.printStackTrace();
                    } else {
                        System.out.println("createFirmbankingRequestCommand completed, Aggregate ID: " + result.toString());

                        // Request Firmbanking 의 DB save
                        FirmbankingRequestJpaEntity requestedEntity = requestFirmbankingPort.createFirmbankingRequest(
                                new FirmbankingRequest.FromBankName(command.getFromBankName()),
                                new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                                new FirmbankingRequest.ToBankName(command.getToBankName()),
                                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                                new FirmbankingRequest.FirmbankingStatus(0),
                                new FirmbankingRequest.FirmbankingAggregateIdentifier(result.toString())
                        );

                        // 은행에 펌뱅킹 요청
                        FirmbankingResult firmbankingResult = requestExternalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                                command.getFromBankName(),
                                command.getFromBankAccountNumber(),
                                command.getToBankName(),
                                command.getToBankAccountNumber(),
                                command.getMoneyAmount()
                        ));

                        // 결과에 따라서 DB save
                        // 3. 결과에 따라서 1번에서 작성했던 FirmbankingRequest 정보를 Update
                        if (firmbankingResult.getResultCode() == 0){
                            // 성공
                            requestedEntity.setFirmbankingStatus(1);
                        } else {
                            // 실패
                            requestedEntity.setFirmbankingStatus(2);
                        }

                        requestFirmbankingPort.modifyFirmbankingRequest(requestedEntity);
                    }
                }
        );
        // Command -> Event Sourcing
    }

    @Override
    public void updateFirmbankingByEvent(UpdateFirmbankingCommand command) {
        // command.
        UpdateFirmbankingRequestCommand updateFirmbankingRequestCommand =
                new UpdateFirmbankingRequestCommand(command.getFirmbankingAggregateIdentifier(), command.getFirmbankingStatus());

        commandGateway.send(updateFirmbankingRequestCommand)
                .whenComplete((result, throwable) -> {
                    if (throwable != null){
                        // 실패
                        throwable.printStackTrace();
                    } else {
                        System.out.println("updateFirmbankingRequestCommand completed, Aggregate ID: " + result.toString());
                        FirmbankingRequestJpaEntity entity = requestFirmbankingPort.getFirmbankingRequest(
                                new FirmbankingRequest.FirmbankingAggregateIdentifier(command.getFirmbankingAggregateIdentifier()));

                        // status 의 변경으로 인한 외부 은행과의 커뮤니케이션
                        // if rollback -> 0, status 변경도 해주겠지만
                        // + 기존 펌뱅킹 정보에서 from <-> to 가 변경된 펌뱅킹을 요청하는 새로운 요청
                        entity.setFirmbankingStatus(command.getFirmbankingStatus());
                        requestFirmbankingPort.modifyFirmbankingRequest(entity);
                    }
                });
    }
}
