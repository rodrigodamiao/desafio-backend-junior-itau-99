package com.damzik.desafio_backend_junior_itau_99.controllers;

import com.damzik.desafio_backend_junior_itau_99.entities.Estatistica;
import com.damzik.desafio_backend_junior_itau_99.entities.Transacao;
import com.damzik.desafio_backend_junior_itau_99.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
public class TransacaoController {

    Transacao transacao = new Transacao();

    @Autowired
    TransacaoService transacaoService;

    @PostMapping(value = "/transacao")
    public ResponseEntity<Void> addTransacao(@RequestBody Transacao transacao) {
        transacaoService.addTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/estatistica")
    public Estatistica getEstatisticas(){
        return transacaoService.getEstatisticas();
    }

    @GetMapping(value = "/transacao/list")
    public List<Transacao> getTransacaoList(){
        return transacaoService.getTransacaoList();
    }

    @DeleteMapping(value = "/transacao")
    public ResponseEntity<Void> deletarTransacoes(){
        transacaoService.deletarTransacoes();
        return ResponseEntity.ok().build();
    }
}
