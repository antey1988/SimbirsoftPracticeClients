package com.example.SimbirsoftPricticeClients.controllets;

import com.example.SimbirsoftPricticeClients.exceptions.NotEnoughMoneyException;
import com.example.SimbirsoftPricticeClients.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<String> handleNotEnoughMoney(NotEnoughMoneyException e) {
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(e.getMessage());
    }
}
