package io.github.fubanko.fubanko.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidAmountException extends FubankoException{

    @Override
    public ProblemDetail toProblemDetail() {

        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Invalid Amount");
        pb.setDetail("The transfer amount should be higher than 0 (zero)");

        return pb;
    }
}
