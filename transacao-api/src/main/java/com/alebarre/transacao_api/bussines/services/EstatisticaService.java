package com.alebarre.transacao_api.bussines.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alebarre.transacao_api.bussines.controllers.dtos.EstatisticasResponseDTO;
import com.alebarre.transacao_api.bussines.controllers.dtos.TransacaoRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticaService {

    public final TransacaoService trasacaoService;

    public EstatisticasResponseDTO calcularEstatisticasDeTransacoes(Integer intervaloBusca) {

        log.info("Iniciando a busca das estatisticas das transações pelo periodo de tempo." + intervaloBusca);
        List<TransacaoRequestDTO> transacoes = trasacaoService.buscarTransacoes(intervaloBusca);

        if (transacoes.isEmpty()) {
            return new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
        .mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();

        log.info("Estatisticas das transações retornadas com sucesso: {}", estatisticasTransacoes);
        return new EstatisticasResponseDTO(
            estatisticasTransacoes.getCount(),
            estatisticasTransacoes.getSum(),
            estatisticasTransacoes.getAverage(),
            estatisticasTransacoes.getMin(),
            estatisticasTransacoes.getMax()
        );

    }

}
