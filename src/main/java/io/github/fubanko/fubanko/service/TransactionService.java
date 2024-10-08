package io.github.fubanko.fubanko.service;

import io.github.fubanko.fubanko.exception.InsufficientBalanceException;
import io.github.fubanko.fubanko.exception.InvalidAmountException;
import io.github.fubanko.fubanko.domain.Status;
import io.github.fubanko.fubanko.domain.Transaction;
import io.github.fubanko.fubanko.domain.dto.TransactionRequest;
import io.github.fubanko.fubanko.repository.TransactionRepository;
import io.github.fubanko.fubanko.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    public Transaction transfer(TransactionRequest transactionRequest) {


        var payer = userRepository.findByUsername(transactionRequest.payer())
                .orElseThrow(() -> new UsernameNotFoundException("Payer was not found"));

        var payee = userRepository.findByUsername(transactionRequest.payee())
                .orElseThrow(() -> new UsernameNotFoundException("Payee was not found"));

        if (payer.getUsername().equals(payee.getUsername())) {
            throw new IllegalArgumentException("Payer and Payee are the same.");
        }

        if (transactionRequest.amount().doubleValue() <= 0) {
            throw new InvalidAmountException();
        }

        if (!payer.isBalanceEqualOrGreaterThan(transactionRequest.amount())) {
            throw new InsufficientBalanceException();
        }

        Transaction transaction = Transaction.builder()
                .amount(transactionRequest.amount())
                .payer(payer)
                .recipient(payee)
                .status(Status.PENDING)
                .build();

        userService.debit(payer.getUsername(), transactionRequest.amount());
        userService.credit(payee.getUsername(), transactionRequest.amount());
        transaction.setStatus(Status.SUCCESS);

        return transactionRepository.save(transaction);
    }

    public List<Transaction> findUserTransactions(String username) {

        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return transactionRepository.findAllByPayerIdOrRecipientId(user.getId(), user.getId());
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }





}
