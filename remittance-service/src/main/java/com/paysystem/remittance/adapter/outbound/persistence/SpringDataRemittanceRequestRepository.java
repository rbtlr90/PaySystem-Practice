package com.paysystem.remittance.adapter.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRemittanceRequestRepository extends JpaRepository<RemittanceRequestJpaEntity, Long> {
}
