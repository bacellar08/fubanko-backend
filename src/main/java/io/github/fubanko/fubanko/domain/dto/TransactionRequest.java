package io.github.fubanko.fubanko.domain.dto;

import java.math.BigDecimal;

public record TransactionRequest(String payer, String payee, BigDecimal amount) {
}
