package io.github.fubanko.fubanko.controller;

import io.github.fubanko.fubanko.exception.FubankoException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(FubankoException.class)
    public ProblemDetail handleFubankoException(FubankoException e) {
        return e.toProblemDetail();
    }
}
