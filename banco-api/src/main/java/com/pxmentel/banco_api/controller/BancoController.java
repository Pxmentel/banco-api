package com.pxmentel.banco_api.controller;

import com.pxmentel.banco_api.domain.entity.Conta;
import com.pxmentel.banco_api.dto.request.CriarContaRequest;
import com.pxmentel.banco_api.dto.request.DepositoRequest;
import com.pxmentel.banco_api.dto.request.SaqueRequest;
import com.pxmentel.banco_api.dto.request.TransferenciaRequest;
import com.pxmentel.banco_api.dto.response.ContaResponse;
import com.pxmentel.banco_api.mapper.ContaMapper;
import com.pxmentel.banco_api.service.BancoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banco")
public class BancoController {
  private final BancoService bancoService;
  public BancoController(BancoService bancoService){
    this.bancoService = bancoService;
  }

  @PostMapping("/contas")
  public ResponseEntity<ContaResponse> criarConta(@Valid @RequestBody CriarContaRequest request) {

    Conta contaCriada = bancoService.criarConta(request);

    ContaResponse response = ContaMapper.toResponse(contaCriada);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(response);
  }

  @GetMapping("/contas")
  public List<ContaResponse> listarContas() {
    return bancoService.listarContas()
        .stream()
        .map(ContaMapper::toResponse)
        .toList();
  }

  @GetMapping("/contas/{numero}")
  public ResponseEntity<ContaResponse> buscarConta(@PathVariable String numero) {
    ContaResponse response = ContaMapper.toResponse(
        bancoService.buscarConta(numero)
    );
    return ResponseEntity.ok(response);
  }

  @GetMapping("/contas/quantidade")
  public ResponseEntity<Integer> quantidadeDeContas() {
    return ResponseEntity.ok(bancoService.quantidadeDeContas());
  }

  @GetMapping("/contas/saldo-total")
  public ResponseEntity<Double> saldoTotal() {
    return ResponseEntity.ok(bancoService.saldoTotal());
  }

  @GetMapping("/contas/maior-saldo")
  public ResponseEntity<ContaResponse> contaComMaiorSaldo() {
    ContaResponse response = ContaMapper.toResponse(
        bancoService.contaComMaiorSaldo()
    );
    return ResponseEntity.ok(response);
  }

  @GetMapping("/contas/saldo-minimo/{valor}")
  public List<ContaResponse> contasComSaldoMinimo(@PathVariable double valor) {
    return bancoService.contasComSaldoMinimo(valor)
        .stream()
        .map(ContaMapper::toResponse)
        .toList();
  }

  @PostMapping("/contas/{numero}/deposito")
  public ResponseEntity<ContaResponse> depositar(
      @PathVariable String numero,
      @Valid @RequestBody DepositoRequest request
      ) {

    Conta conta = bancoService.depositar(numero, request.getValor());

    return ResponseEntity.ok(
        ContaMapper.toResponse(conta)
    );
  }

  @PostMapping("/contas/{numero}/saque")
  public ResponseEntity<ContaResponse> sacar(
      @PathVariable String numero,
      @Valid @RequestBody SaqueRequest request
  ) {

    Conta conta = bancoService.sacar(numero, request.getValor());

    return ResponseEntity.ok(
        ContaMapper.toResponse(conta)
    );
  }

  @PostMapping("/contas/transferencia")
  public ResponseEntity<Void> transferir(
      @Valid @RequestBody TransferenciaRequest request
  ) {

    bancoService.transferir(
        request.getContaOrigem(),
        request.getContaDestino(),
        request.getValor()
    );

    return ResponseEntity.ok().build();
  }

}
