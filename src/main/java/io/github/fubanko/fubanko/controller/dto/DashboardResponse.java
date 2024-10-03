package io.github.fubanko.fubanko.controller.dto;



import io.github.fubanko.fubanko.domain.User;
import io.github.fubanko.fubanko.domain.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.List;


public record DashboardResponse(String username,
                                BigDecimal balance,
                                List<TransactionDTO> transactions) {



    public static DashboardResponse fromUser(User user, List<TransactionDTO> transactions) {
        return new DashboardResponse(user.getUsername(), user.getBalance(), transactions);
    }
}
