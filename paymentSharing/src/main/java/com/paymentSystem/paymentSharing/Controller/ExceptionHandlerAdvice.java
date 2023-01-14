package com.paymentSystem.paymentSharing.Controller;

import com.paymentSystem.paymentSharing.Exception.BaseException;
import com.paymentSystem.paymentSharing.Exception.InsertException;
import com.paymentSystem.paymentSharing.Exception.UpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> internalServerErrorHandler(Exception e) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body("Internal server error");
    }

    @ExceptionHandler(InsertException.class)
    public ResponseEntity<?> insert(BaseException e) {
        return buildExceptionResponse(INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(UpdateException.class)
    public ResponseEntity<?> update(BaseException e) {
        return buildExceptionResponse(BAD_REQUEST, e);
    }
    private ResponseEntity<?> buildExceptionResponse(HttpStatus httpStatus, BaseException exception) {
        return ResponseEntity.status(httpStatus).body(exception.getMessage());
    }

}
