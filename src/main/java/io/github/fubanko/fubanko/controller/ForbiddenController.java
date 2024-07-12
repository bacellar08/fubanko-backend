package io.github.fubanko.fubanko.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForbiddenController {

    @GetMapping("/forbidden")
    public String forbidden() {
        return "Forbidden";
    }
}
