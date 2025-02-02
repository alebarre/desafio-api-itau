package com.alebarre.transacao_api.bussines.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alebarre.transacao_api.bussines.controllers.dtos.TransacaoRequestDTO;
import com.alebarre.transacao_api.infrastructure.exceptions.UnprocessableEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listTransacoes = new ArrayList<>();

    public void addTransacao(TransacaoRequestDTO dto) {

        log.info("Iniciado o processamento da transação: {}", dto);

        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.info("Data e hota maiores que data e hora atuais.");
            throw new UnprocessableEntity("Data e hota maiores que data e hora atuais.");
        }

        if(dto.valor() < 0) {
            log.info("Valor não pode ser menor que zero.");
            throw new UnprocessableEntity("Valor não pode ser menor que 0");
        }

        log.info("Transação processada com sucesso: {}", dto);
        listTransacoes.add(dto);
    }

    public void limparTransacoes() {
        log.info("Iniciado o processo de limpeza das transações.");
        listTransacoes.clear();
    }


    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca){
        log.info("Iniciando a busca das transações pelo periodo de tempo: {}", intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retornando as transações realizadas com sucesso após o intervalo de tempo: {}", dataHoraIntervalo);
        return listTransacoes.stream()
            .filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo))
            .toList(); 

    }
}
