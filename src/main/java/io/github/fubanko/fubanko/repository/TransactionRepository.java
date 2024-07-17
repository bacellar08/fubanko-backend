package io.github.fubanko.fubanko.repository;

import io.github.fubanko.fubanko.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
