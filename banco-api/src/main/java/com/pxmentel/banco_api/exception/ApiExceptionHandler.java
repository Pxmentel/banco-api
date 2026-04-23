package com.pxmentel.banco_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {
  @ExceptionHandler(AccountNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleContaNaoEncontrada(
      AccountNotFoundException ex
  ) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        Map.of(
            "timestamp", LocalDateTime.now(),
            "status", 404,
            "error", "Account não encontrada",
            "message", ex.getMessage()
        )
    );
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, Object>> handleIllegalArgument(
      IllegalArgumentException ex
  ) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        Map.of(
            "timestamp", LocalDateTime.now(),
            "status", 400,
            "error", "Erro de validação",
            "message", ex.getMessage()
        )
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationErrors(
      MethodArgumentNotValidException ex
  ) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        Map.of(
            "timestamp", LocalDateTime.now(),
            "status", 400,
            "error", "Erro de validação",
            "messages", errors
        )
    );
  }
}