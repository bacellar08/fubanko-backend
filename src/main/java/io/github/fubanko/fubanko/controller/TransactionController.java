package io.github.fubanko.fubanko.controller;

import io.github.fubanko.fubanko.model.Transaction;
import io.github.fubanko.fubanko.model.dto.TransactionRequest;
import io.github.fubanko.fubanko.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> transfer(@RequestBody TransactionRequest transactionRequest) {

        var transaction = transactionService.transfer(transactionRequest);

        return ResponseEntity.ok(transaction);
    }
}
