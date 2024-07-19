package io.github.fubanko.fubanko.controller;

import io.github.fubanko.fubanko.model.Transaction;
import io.github.fubanko.fubanko.model.dto.TransactionDTO;
import io.github.fubanko.fubanko.model.dto.TransactionRequest;
import io.github.fubanko.fubanko.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody TransactionRequest transactionRequest) {

        var transaction = transactionService.transfer(transactionRequest);

        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {

        var transactions = transactionService.findAll();

        var transactionsDto = transactions.stream().map(TransactionDTO::fromTransaction).toList();

        return ResponseEntity.ok(transactionsDto);
    }
}
