package com.damzik.desafio_backend_junior_itau_99.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Para exceções de campos ausentes
    @ExceptionHandler(CamposAusentesException.class)
    public ResponseEntity<String> handleCamposAusentes(CamposAusentesException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
    }

    // Para exceções de transação no futuro
    @ExceptionHandler(TransacaoNoFuturoException.class)
    public ResponseEntity<String> handleTransacaoNoFuturo(TransacaoNoFuturoException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build(); // 422 Unprocessable Entity
    }

    // Para exceções de valor negativo
    @ExceptionHandler(ValorNegativoException.class)
    public ResponseEntity<String> handleValorNegativo(ValorNegativoException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build(); // 422 Unprocessable Entity
    }

    // Para qualquer outra exceção genérica
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
    }
}