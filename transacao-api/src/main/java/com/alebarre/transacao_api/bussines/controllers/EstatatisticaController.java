package com.alebarre.transacao_api.bussines.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alebarre.transacao_api.bussines.controllers.dtos.EstatisticasResponseDTO;
import com.alebarre.transacao_api.bussines.services.EstatisticaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatatisticaController {

    private final EstatisticaService estatisticaService;

    @GetMapping
    @Operation(description = "Busca as estatisticas das transações")
    @ApiResponse(responseCode = "200", description = "Estatisticas retornadas com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro de requisição")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(@RequestParam (value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatisticaService.calcularEstatisticasDeTransacoes(intervaloBusca));
    }

}
