package io.github.fubanko.fubanko.controller;

import io.github.fubanko.fubanko.domain.Status;
import io.github.fubanko.fubanko.domain.Transaction;
import io.github.fubanko.fubanko.domain.dto.TransactionDTO;
import io.github.fubanko.fubanko.domain.dto.TransactionRequest;
import io.github.fubanko.fubanko.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static io.github.fubanko.fubanko.mock.MockTransaction.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TransactionControllerTest {

    @Mock
    TransactionService transactionService;
    @InjectMocks
    TransactionController transactionController;

    TransactionRequest transactionRequest;
    Transaction transaction;
    List<Transaction> transactions;
    List<TransactionDTO> transactionDTOs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        transactionRequest = mockTransactionRequest();
        transaction = mockTransaction();
        transactions = mockTransactions();
        transactionDTOs = List.of(TransactionDTO.fromTransaction(transaction));
    }

    @Test
    @DisplayName("Should return transaction SUCCESS and Status 200 OK.")
    void transfer() {

        when(transactionService.transfer(transactionRequest)).thenReturn(transaction);

        ResponseEntity<Transaction> response = transactionController.transfer(transactionRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction, response.getBody());
        assertEquals(Status.SUCCESS, transaction.getStatus());
    }

    @Test
    @DisplayName("Should return all transactions")
    void getAllTransactions() {

        when(transactionService.findAll()).thenReturn(transactions);

        ResponseEntity<List<TransactionDTO>> response = transactionController.getAllTransactions();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactionDTOs, response.getBody());
        assertEquals(1, response.getBody().size());
    }
}