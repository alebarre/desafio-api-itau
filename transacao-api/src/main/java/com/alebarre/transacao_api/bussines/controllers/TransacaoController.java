package com.alebarre.transacao_api.bussines.controllers;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alebarre.transacao_api.bussines.controllers.dtos.TransacaoRequestDTO;
import com.alebarre.transacao_api.bussines.services.TransacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(description = "Cria uma nova transação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
        @ApiResponse(responseCode = "422", description = "Campos não atendem requisitos da transação"),
        @ApiResponse(responseCode = "400", description = "Erro de requisição"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> criarTransacao(@RequestBody TransacaoRequestDTO transacaoRequestDTO) {
        transacaoService.addTransacao(transacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Deleta todas as transações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transações deletadas com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletarTransacoes() {
        transacaoService.limparTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
