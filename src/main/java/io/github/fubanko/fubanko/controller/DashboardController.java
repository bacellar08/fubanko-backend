package io.github.fubanko.fubanko.controller;

import io.github.fubanko.fubanko.controller.dto.DashboardResponse;
import io.github.fubanko.fubanko.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponse> getUserDashboard(@RequestParam String username) {

        var user = DashboardResponse.fromUser(userService.findByUsername(username));

        return ResponseEntity.status(HttpStatus.OK).body(user);








    }
}
