package com.paysystem.remittance.adapter.inbound.web;

import com.paysystem.common.WebAdapter;
import com.paysystem.remittance.application.port.inbound.FindRemittanceCommand;
import com.paysystem.remittance.application.port.inbound.FindRemittanceUseCase;
import com.paysystem.remittance.domain.RemittanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindRemittanceHistoryController {

    private final FindRemittanceUseCase findRemittanceUseCase;
    @GetMapping( "/remittance/{membershipId}")
    List<RemittanceRequest> findRemittanceHistory(@PathVariable String membershipId) {
        FindRemittanceCommand command = FindRemittanceCommand.builder()
                .membershipId(membershipId)
                .build();

        return findRemittanceUseCase.findRemittanceHistory(command);
    }
}
