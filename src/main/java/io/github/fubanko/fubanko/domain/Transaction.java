package io.github.fubanko.fubanko.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "tb_transactions")
@Data
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime dateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    private User payer;
        @ManyToOne(fetch = FetchType.EAGER)
    private User recipient;

    private String message;

    public Transaction() {
    }
}
