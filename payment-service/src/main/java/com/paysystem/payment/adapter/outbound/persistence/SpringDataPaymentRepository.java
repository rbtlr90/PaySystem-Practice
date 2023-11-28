package com.paysystem.payment.adapter.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPaymentRepository extends JpaRepository<PaymentJpaEntity, Long> {
}
