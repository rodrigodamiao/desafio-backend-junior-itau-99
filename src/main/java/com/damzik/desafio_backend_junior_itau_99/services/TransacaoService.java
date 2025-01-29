package com.damzik.desafio_backend_junior_itau_99.services;

import com.damzik.desafio_backend_junior_itau_99.entities.Estatistica;
import com.damzik.desafio_backend_junior_itau_99.entities.Transacao;
import com.damzik.desafio_backend_junior_itau_99.exceptions.CamposAusentesException;
import com.damzik.desafio_backend_junior_itau_99.exceptions.TransacaoNoFuturoException;
import com.damzik.desafio_backend_junior_itau_99.exceptions.ValorNegativoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    private List<Transacao> transacaoList = new ArrayList<>();

    //Post
    public Transacao addTransacao(Transacao transacao){
        transacao.setDataHora(OffsetDateTime.now());

        // Verificar se os campos obrigatórios estão preenchidos
            if (transacao.getValor() == null) {
            throw new CamposAusentesException("Os campos valor e dataHora são obrigatórios");
        }

        // Verificar se a transação não é no futuro
        if (transacao.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new TransacaoNoFuturoException("A transação não pode ser no futuro");
        }

        // Verificar se o valor não é negativo
        if (transacao.getValor() <= 0) {
            throw new ValorNegativoException("O valor da transação não pode ser negativo ou zero");
        }

        transacaoList.add(transacao);

        return transacao;
    }

    //Get
    public List<Transacao> getTransacaoList(){
        return transacaoList;
    }

    //Get (Estatísticas da lista de transação)
    public Estatistica getEstatisticas(){
        Estatistica estatistica = new Estatistica();
        double min = 999999;
        double max = 0;

        long segundosPassados = 0;

        int count = 0;

        double sum = 0;

        for(Transacao transacao : transacaoList){
            segundosPassados = Duration.between(transacao.getDataHora(), OffsetDateTime.now()).getSeconds();

            if(segundosPassados < 60){
                sum+=transacao.getValor();
                if(transacao.getValor() < min) min = transacao.getValor();
                if(transacao.getValor() > max) max = transacao.getValor();

                count++;
            }
        }

        estatistica.setSum(sum);
        estatistica.setCount(count);
        estatistica.setAvg(sum/count);
        estatistica.setMin(min);
        estatistica.setMax(max);

        //if count == 0
        if(count == 0){
            estatistica.setCount(0);
            estatistica.setSum(0);
            estatistica.setAvg(0);
            estatistica.setMin(0);
            estatistica.setMax(0);
        }

        return estatistica;
    }

    public void deletarTransacoes(){
        transacaoList.clear();
    }
}
