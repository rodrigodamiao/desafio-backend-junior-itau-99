package com.damzik.desafio_backend_junior_itau_99.exceptions;

public class TransacaoNoFuturoException extends RuntimeException {
    public TransacaoNoFuturoException(String message) {
        super(message);
    }
}