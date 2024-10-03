package io.github.fubanko.fubanko.service;

import io.github.fubanko.fubanko.domain.Status;
import io.github.fubanko.fubanko.domain.Transaction;
import io.github.fubanko.fubanko.domain.User;
import io.github.fubanko.fubanko.domain.dto.TransactionRequest;
import io.github.fubanko.fubanko.repository.TransactionRepository;
import io.github.fubanko.fubanko.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static io.github.fubanko.fubanko.mock.MockTransaction.*;
import static io.github.fubanko.fubanko.mock.MockUser.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    UserService userService;
    @InjectMocks
    TransactionService transactionService;

    User user1, user2;
    Transaction transaction;
    TransactionRequest transactionRequest;
    List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user1 = mockUser();
        user2 = mockUser(2);
        transactionRequest = mockTransactionRequest();
        transaction = mockTransaction();
        transactions = mockTransactions();
    }

    @Test
    @DisplayName("Should return transaction with status SUCCESS")
    void transferSuccess() {

        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(user1));
        when(userRepository.findByUsername(USERNAME2)).thenReturn(Optional.of(user2));
        when(transactionRepository.save(transaction))
                .thenReturn(transaction);

        user1.setBalance(user1.getBalance().subtract(transaction.getAmount()));
        user2.setBalance(user2.getBalance().add(transaction.getAmount()));

        transactionService.transfer(transactionRequest);

        assertNotNull(transaction);
        assertEquals(Status.SUCCESS, transaction.getStatus());
        verify(userService).debit(USERNAME, BigDecimal.valueOf(5));
        verify(userService).credit(USERNAME2, BigDecimal.valueOf(5));
        assertEquals(BigDecimal.valueOf(5), user1.getBalance());
        assertEquals(BigDecimal.valueOf(15), user2.getBalance());
    }

    @Test
    @DisplayName("Should return transactions related to the user name")
    void findUserTransactions() {

        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(user1));
        when(transactionRepository.findAllByPayerIdOrRecipientId(user1.getId(), user1.getId()))
                .thenReturn(transactions);

        assertNotNull(transactionService.findUserTransactions(USERNAME));
        assertEquals(transactions, transactionService.findUserTransactions(USERNAME));
        assertEquals(1, transactions.size());


    }

    @Test
    @DisplayName("Should return all transactions")
    void findAll() {

        when(transactionRepository.findAll()).thenReturn(transactions);

        assertNotNull(transactionService.findAll());
    }
}