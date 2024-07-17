package io.github.fubanko.fubanko.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsufficientBalanceException extends FubankoException {

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Insufficient Balance");
        pb.setDetail("Payer has insufficient funds to perform this operation");

        return pb;
    }
}
