package io.github.fubanko.fubanko.mock;

import io.github.fubanko.fubanko.domain.User;

import java.math.BigDecimal;
import java.util.List;

public class MockUser {

    public static Long USER_ID = 1L;
    public static Long USER_ID2 = 2L;
    public static String USERNAME = "user-test";
    public static String USERNAME2 = "user-test2";
    public static String PASSWORD = "password";
    public static BigDecimal USER_BALANCE = BigDecimal.TEN;


    public static User mockUser(int id) {
        return new User(USER_ID2, USERNAME2, PASSWORD, USER_BALANCE);
    }

    public static User mockUser() {
        return new User(USER_ID, USERNAME, PASSWORD, USER_BALANCE);
    }

    public static List<User> mockUsers() {
        return List.of(mockUser());
    }
}
