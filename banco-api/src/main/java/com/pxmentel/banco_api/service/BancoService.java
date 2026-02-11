package com.pxmentel.banco_api.service;

import com.pxmentel.banco_api.domain.entity.Cliente;
import com.pxmentel.banco_api.domain.entity.Conta;
import com.pxmentel.banco_api.domain.entity.ContaCorrente;
import com.pxmentel.banco_api.exception.ContaNaoEncontradaException;
import com.pxmentel.banco_api.dto.request.CriarContaRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BancoService {
  private final Map<String, Conta> contas;
  private final Map<String, Cliente> clientes;

  public BancoService() {
    this.contas = new HashMap<>();
    this.clientes = new HashMap<>();
  }

  public Conta criarConta (CriarContaRequest request) {
    //Valida se request é vazia
    if (request == null) {
      throw new IllegalArgumentException("Dados da conta são obrigatórios");
    }

    Cliente cliente = clientes.get(request.getDocumentoCliente());

    //Valida se o cliente já existe e se ele existir vai atribuir o cliente
    if (cliente == null){
      cliente = new Cliente(
          request.getNomeCliente(),
          request.getDocumentoCliente()
      );
      clientes.put(cliente.getCpf(), cliente);
    }

    //Valida se a conta já existe
    if (contas.containsKey(request.getNumero())) {
      throw new IllegalArgumentException("Conta já existe");
    }

    //Criar Conta caso não exista
    Conta conta = new ContaCorrente(
        request.getNumero(),
        cliente
    );

    contas.put(conta.getNumero(), conta);

    return conta;
  }

  public Conta buscarConta (String numero) {
    Conta conta = contas.get(numero);

    if (conta == null){
      throw new ContaNaoEncontradaException(
          "Conta não encontrada: " + numero
      );
    }

    return conta;
  }

  public double saldoTotal() {
    double total = 0.0;

    for (Conta conta : contas.values()) {
      total += conta.getSaldo();
    }

    return total;
  }

  public List<Conta> listarContas() {
    return new ArrayList<>(contas.values());
  }

  public int quantidadeDeContas() {
    return contas.size();
  }

  public Conta contaComMaiorSaldo() {
    double saldoDaConta = 0.0;
    Conta contaMaiorSaldo = null;

    for (Conta conta : contas.values()){
      if (contas.isEmpty()){
        throw new IllegalArgumentException("Não existem contas cadastradas");
      }
      if (saldoDaConta < conta.getSaldo()){
        saldoDaConta = conta.getSaldo();
        contaMaiorSaldo = conta;
      }
    }
    return contaMaiorSaldo;
  }

  public List<Conta> contasComSaldoMinimo(double valorMinimo) {
    List<Conta> contasValidas = new ArrayList<>();
    for (Conta conta : contas.values()){
      if (conta.getSaldo() >= valorMinimo){
        contasValidas.add(conta);
      }
    }
    return contasValidas;
  }

  public Conta depositar(String numeroConta, double valor) {
    Conta conta = buscarConta(numeroConta);

    conta.depositar(valor);

    return conta;
  }

  public Conta sacar(String numeroConta, double valor) {
    Conta conta = buscarConta(numeroConta);

    conta.sacar(valor);

    return conta;
  }

  public void transferir(String origem, String destino, double valor) {

    if (valor <= 0) {
      throw new IllegalArgumentException("Valor inválido");
    }

    Conta contaOrigem = buscarConta(origem);
    Conta contaDestino = buscarConta(destino);

    contaOrigem.sacar(valor);
    contaDestino.depositar(valor);
  }

}
