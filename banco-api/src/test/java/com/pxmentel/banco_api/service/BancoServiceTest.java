package com.pxmentel.banco_api.service;

import com.pxmentel.banco_api.dto.request.CriarContaRequest;
import com.pxmentel.banco_api.domain.entity.Conta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BancoServiceTest {

  private BancoService bancoService;

  @BeforeEach
  void setup() {
    bancoService = new BancoService();
  }

  @Test
  void deveCriarContaComSucesso() {

    CriarContaRequest req = new CriarContaRequest();
    req.setNumero("1");
    req.setNomeCliente("Vinicius");
    req.setDocumentoCliente("123");

    Conta conta = bancoService.criarConta(req);

    assertNotNull(conta);
    assertEquals("1", conta.getNumero());
  }

  @Test
  void deveDepositarValor() {

    CriarContaRequest req = new CriarContaRequest();
    req.setNumero("1");
    req.setNomeCliente("Vinicius");
    req.setDocumentoCliente("123");

    bancoService.criarConta(req);

    bancoService.depositar("1", 100);

    Conta conta = bancoService.buscarConta("1");

    assertEquals(100, conta.getSaldo());
  }

  @Test
  void deveSacarValor() {

    CriarContaRequest req = new CriarContaRequest();
    req.setNumero("1");
    req.setNomeCliente("Vinicius");
    req.setDocumentoCliente("123");

    bancoService.criarConta(req);
    bancoService.depositar("1", 200);

    bancoService.sacar("1", 50);

    Conta conta = bancoService.buscarConta("1");

    assertEquals(150, conta.getSaldo());
  }

  @Test
  void deveTransferirEntreContas() {

    CriarContaRequest a = new CriarContaRequest();
    a.setNumero("1");
    a.setNomeCliente("A");
    a.setDocumentoCliente("1");

    CriarContaRequest b = new CriarContaRequest();
    b.setNumero("2");
    b.setNomeCliente("B");
    b.setDocumentoCliente("2");

    bancoService.criarConta(a);
    bancoService.criarConta(b);

    bancoService.depositar("1", 300);
    bancoService.transferir("1","2",100);

    assertEquals(200, bancoService.buscarConta("1").getSaldo());
    assertEquals(100, bancoService.buscarConta("2").getSaldo());
  }

  @Test
  void deveFalharAoBuscarContaInexistente() {

    assertThrows(
        RuntimeException.class,
        () -> bancoService.buscarConta("999")
    );
  }

  @Test
  void deveFalharTransferenciaSemSaldo() {

    CriarContaRequest a = new CriarContaRequest();
    a.setNumero("1");
    a.setNomeCliente("A");
    a.setDocumentoCliente("1");

    CriarContaRequest b = new CriarContaRequest();
    b.setNumero("2");
    b.setNomeCliente("B");
    b.setDocumentoCliente("2");

    bancoService.criarConta(a);
    bancoService.criarConta(b);

    assertThrows(
        RuntimeException.class,
        () -> bancoService.transferir("1","2",999)
    );
  }

}
