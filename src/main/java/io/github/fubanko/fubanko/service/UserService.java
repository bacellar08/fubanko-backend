package io.github.fubanko.fubanko.service;

import io.github.fubanko.fubanko.domain.User;
import io.github.fubanko.fubanko.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public void credit(String username, BigDecimal amount) {

        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        user.setBalance(user.getBalance().add(amount));
        userRepository.save(user);

    }

    public void debit(String username, BigDecimal amount) {

        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

                user.setBalance(user.getBalance().subtract(amount));
                userRepository.save(user);

    }
}
