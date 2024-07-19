package io.github.fubanko.fubanko.repository;

import io.github.fubanko.fubanko.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findAllByPayerIdOrRecipientId(Long payer_id, Long recipient_id);
}
