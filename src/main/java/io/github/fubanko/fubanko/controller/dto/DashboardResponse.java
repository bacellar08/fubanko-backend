package io.github.fubanko.fubanko.controller.dto;

import io.github.fubanko.fubanko.model.User;

import java.math.BigDecimal;

public record DashboardResponse(String username, BigDecimal balance) {

    public static DashboardResponse fromUser(User user) {
        return new DashboardResponse(user.getUsername(), user.getBalance());
    }
}
