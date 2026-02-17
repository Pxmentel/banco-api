package com.pxmentel.banco_api.exception;
import com.pxmentel.banco_api.exception.ContaNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<String>> handleValidationErrors(
      MethodArgumentNotValidException ex) {

    List<String> erros = new ArrayList<>();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(error ->
            erros.add(error.getField() + ": " + error.getDefaultMessage())
        );

    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(erros);
  }

  @ExceptionHandler(ContaNaoEncontradaException.class)
  public ResponseEntity<String> handleContaNaoEncontrada(
      ContaNaoEncontradaException ex
  ) {

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ex.getMessage());
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ex.getMessage());
  }

}