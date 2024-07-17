package io.github.fubanko.fubanko.service;

import io.github.fubanko.fubanko.model.User;
import io.github.fubanko.fubanko.repository.UserRepository;
import io.github.fubanko.fubanko.service.dto.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public String authenticate(RegisterRequest registerRequest) {

        try {

            var username = registerRequest.username();
            var password = registerRequest.password();


            var authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            return jwtService.generateToken(authentication);

        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username or password", e);
        }
    }

    public User register(RegisterRequest registerRequest) {

        var user = new User();
        var encryptedPassword = passwordEncoder.encode(registerRequest.password());

        user.setUsername(registerRequest.username());
        user.setPassword(encryptedPassword);

        return  userRepository.save(user);

    }
}
