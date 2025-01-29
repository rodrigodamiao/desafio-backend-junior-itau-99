package com.damzik.desafio_backend_junior_itau_99.entities;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


public class Transacao {

    private Double valor;
    private OffsetDateTime dataHora;

    public Transacao() {
    }

    public Transacao(Double valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
