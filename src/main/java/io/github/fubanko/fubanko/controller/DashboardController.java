package io.github.fubanko.fubanko.controller;

import io.github.fubanko.fubanko.controller.dto.DashboardResponse;
import io.github.fubanko.fubanko.model.dto.TransactionDTO;
import io.github.fubanko.fubanko.service.TransactionService;
import io.github.fubanko.fubanko.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final UserService userService;
    private final TransactionService transactionService;
    public DashboardController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponse> getUserDashboard(@RequestParam String username) {

        var transactions = transactionService.findUserTransactions(username);

        var transactionsDto = transactions.stream().map(TransactionDTO::fromTransaction).collect(Collectors.toList());

        var user = userService.findByUsername(username);

        var response = DashboardResponse.fromUser(user, transactionsDto);

      return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
