package io.github.fubanko.fubanko.model.dto;

import java.math.BigDecimal;

public record TransactionRequest(String payer, String payee, BigDecimal amount) {
}
