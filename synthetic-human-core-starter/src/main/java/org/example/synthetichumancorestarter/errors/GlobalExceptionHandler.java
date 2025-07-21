package org.example.synthetichumancorestarter.errors;

import org.example.synthetichumancorestarter.commands.QueueOverflowException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(QueueOverflowException.class)
    public ResponseEntity<ErrorResponse> handleOverflow(QueueOverflowException ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ErrorResponse(
                        Instant.now(),
                        HttpStatus.TOO_MANY_REQUESTS.value(),
                        "Queue overflow",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(
                        Instant.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Validation failed",
                        message
                ));
    }
}
