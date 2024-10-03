package io.github.fubanko.fubanko.service;

import io.github.fubanko.fubanko.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static io.github.fubanko.fubanko.mock.MockUser.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find user by username")
    void findByUsername() {

        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(mockUser()));

        var user = userService.findByUsername(USERNAME);

        assertNotNull(user);
        assertEquals(USERNAME, user.getUsername());
    }

    @Test
    @DisplayName("Should return all users")
    void findAll() {

        when(userRepository.findAll()).thenReturn(mockUsers());

        var users = userService.findAll();

        assertNotNull(users);
        assertEquals(users.size(), 1);
    }

    @Test
    @DisplayName("Should return user balance plus 5")
    void credit() {

        var user = mockUser();
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(user));

        userService.credit(USERNAME, BigDecimal.valueOf(5));

        assertEquals(BigDecimal.valueOf(15), user.getBalance());
    }

    @Test
    @DisplayName("Should return user balance minus 5")
    void debit() {

        var user = mockUser();
        when(userRepository.findByUsername(USERNAME)).thenReturn(Optional.of(user));

        userService.debit(USERNAME, BigDecimal.valueOf(5));

        assertEquals(BigDecimal.valueOf(5), user.getBalance());
    }
}