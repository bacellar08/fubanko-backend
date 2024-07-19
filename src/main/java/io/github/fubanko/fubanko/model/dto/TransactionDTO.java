package io.github.fubanko.fubanko.model.dto;

import io.github.fubanko.fubanko.model.Status;
import io.github.fubanko.fubanko.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(LocalDateTime dateTime, String from, String to, BigDecimal amount, String description, Status status) {


    public static TransactionDTO fromTransaction(Transaction t) {

        return new TransactionDTO(t.getDateTime(),
                t.getPayer().getUsername(),
                t.getRecipient().getUsername(),
                t.getAmount(),
                t.getMessage(),
                t.getStatus());
    }
}
