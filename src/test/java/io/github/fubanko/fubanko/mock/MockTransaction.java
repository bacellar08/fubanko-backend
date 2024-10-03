package io.github.fubanko.fubanko.mock;

import io.github.fubanko.fubanko.domain.Status;
import io.github.fubanko.fubanko.domain.Transaction;
import io.github.fubanko.fubanko.domain.dto.TransactionDTO;
import io.github.fubanko.fubanko.domain.dto.TransactionRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.github.fubanko.fubanko.mock.MockUser.*;

public class MockTransaction {

    public static Transaction mockTransaction() {

        return Transaction.builder()
                .id(UUID.randomUUID())
                .payer(mockUser())
                .recipient(mockUser(2))
                .status(Status.SUCCESS)
                .amount(BigDecimal.valueOf(5))
                .dateTime(LocalDateTime.now())
                .message("Transaction test")
                .build();
    }

    public static TransactionRequest mockTransactionRequest() {

        return new TransactionRequest(USERNAME, USERNAME2, BigDecimal.valueOf(5));
    }

    public static List<Transaction> mockTransactions() {
        return List.of(mockTransaction());
    }

    public static List<TransactionDTO> mockTransactionDTOs() {
        return List.of(TransactionDTO.fromTransaction(mockTransaction()));
    }
}
