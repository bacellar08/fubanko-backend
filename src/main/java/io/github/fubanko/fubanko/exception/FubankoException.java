package io.github.fubanko.fubanko.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class FubankoException extends RuntimeException {

    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("Fubanko detected an Internal Server Error");

        return pb;
    }
}
