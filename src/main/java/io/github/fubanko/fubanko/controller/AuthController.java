package io.github.fubanko.fubanko.controller;

import io.github.fubanko.fubanko.service.AuthService;
import io.github.fubanko.fubanko.service.dto.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegisterRequest registerRequest) throws Exception {

        var token = authService.authenticate(registerRequest);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) throws Exception {

        var user = authService.register(registerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
