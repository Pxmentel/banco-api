package com.pxmentel.banco_api.service;

import com.pxmentel.banco_api.domain.entity.Cliente;
import com.pxmentel.banco_api.domain.entity.Conta;
import com.pxmentel.banco_api.domain.entity.ContaCorrente;
import com.pxmentel.banco_api.domain.entity.ContaPoupanca;
import com.pxmentel.banco_api.domain.enumm.TipoConta;
import com.pxmentel.banco_api.exception.ContaNaoEncontradaException;
import com.pxmentel.banco_api.dto.request.CriarContaRequest;
import com.pxmentel.banco_api.repository.ClienteRepository;
import com.pxmentel.banco_api.repository.ContaRepository; // Importar o novo repository
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BancoService {

  private final ClienteRepository clienteRepository;
  private final ContaRepository contaRepository; // Substitui o Map

  public BancoService(ClienteRepository clienteRepository, ContaRepository contaRepository) {
    this.clienteRepository = clienteRepository;
    this.contaRepository = contaRepository;
  }

  @Transactional
  public Conta criarConta(CriarContaRequest request) {
    if (request == null) throw new IllegalArgumentException("Dados inválidos");

    if (contaRepository.existsByNumero(request.getNumero())) {
      throw new IllegalArgumentException("Conta já existe");
    }

    Cliente cliente = clienteRepository.findByCpf(request.getDocumentoCliente())
        .orElseGet(() -> {
          Cliente novoCliente = new Cliente(request.getNomeCliente(), request.getDocumentoCliente());
          return clienteRepository.save(novoCliente);
        });

    Conta conta;

    if (request.getTipo() == TipoConta.CORRENTE) {
      ContaCorrente cc = new ContaCorrente();
      cc.setLimite(request.getLimite());
      conta = cc;
    } else if (request.getTipo() == TipoConta.POUPANCA) {
      ContaPoupanca cp = new ContaPoupanca();
      cp.setTaxaRendimento(request.getTaxaRendimento());
      conta = cp;
    } else {
      throw new IllegalArgumentException("Tipo de conta inválido");
    }


    conta.setNumero(request.getNumero());
    conta.setCliente(cliente);
    conta.setSaldo(0.0);

    return contaRepository.save(conta);
  }

  public Conta buscarConta(String numero) {
    return contaRepository.findByNumero(numero)
        .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada: " + numero));
  }

  public List<Conta> listarContas() {
    return contaRepository.findAll();
  }

  public int quantidadeDeContas() {
    return (int) contaRepository.count();
  }

  @Transactional
  public Conta depositar(String numeroConta, double valor) {
    Conta conta = buscarConta(numeroConta);
    conta.setSaldo(conta.getSaldo() + valor);
    return contaRepository.save(conta); // Atualiza no banco
  }

  @Transactional
  public Conta sacar(String numeroConta, double valor) {
    Conta conta = buscarConta(numeroConta);
    if (conta.getSaldo() < valor) throw new IllegalArgumentException("Saldo insuficiente");

    conta.setSaldo(conta.getSaldo() - valor);
    return contaRepository.save(conta);
  }

  @Transactional
  public void transferir(String origem, String destino, double valor) {
    Conta contaOrigem = buscarConta(origem);
    Conta contaDestino = buscarConta(destino);

    contaOrigem.sacar(valor);
    contaDestino.depositar(valor);
  }

  // 1. Saldo Total (Muito mais performático!)
  public double saldoTotal() {
    Double total = contaRepository.sumAllSaldos();
    return total != null ? total : 0.0;
  }

  // 2. Conta com maior saldo (O banco já traz a correta)
  public Conta contaComMaiorSaldo() {
    return contaRepository.findTopByOrderBySaldoDesc()
        .orElseThrow(() -> new IllegalArgumentException("Não existem contas cadastradas"));
  }

  // 3. Contas com saldo mínimo (Filtro feito pelo banco)
  public List<Conta> contasComSaldoMinimo(double valorMinimo) {
    return contaRepository.findBySaldoGreaterThanEqual(valorMinimo);
  }
}