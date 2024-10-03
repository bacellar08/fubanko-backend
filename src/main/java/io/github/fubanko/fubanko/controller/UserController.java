package io.github.fubanko.fubanko.controller;

import io.github.fubanko.fubanko.domain.User;
import io.github.fubanko.fubanko.domain.dto.UserInfoResponse;
import io.github.fubanko.fubanko.repository.UserRepository;
import io.github.fubanko.fubanko.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {

        return userService.findAll();

    }

    @GetMapping("/userinfo")
    public ResponseEntity<UserInfoResponse> getUserInfo() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfoResponse userInfo = new UserInfoResponse(username);

        return ResponseEntity.status(HttpStatus.OK).body(userInfo);


    }


}
