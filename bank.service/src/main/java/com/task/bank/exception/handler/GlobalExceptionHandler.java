package com.task.bank.exception.handler;

import com.task.bank.exception.InsufficientMoneyException;
import com.task.bank.exception.NotFoundAccountException;
import com.task.bank.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundAccountException.class)
    public final ResponseEntity<Void> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InsufficientMoneyException.class)
    public final ResponseEntity<String> handleInsufficientMoneyException(InsufficientMoneyException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<String> handleUnauthorizedException(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
