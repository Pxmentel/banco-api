package com.pxmentel.banco_api.domain.entity;

import com.pxmentel.banco_api.domain.enumm.TipoConta;
import com.pxmentel.banco_api.exception.SaldoInsuficienteException;

public class ContaCorrente implements Conta{

  private String numero;
  private Cliente cliente;
  private double saldo;

  public ContaCorrente(String numero, Cliente cliente){
    this.numero = numero;
    this.cliente = cliente;
    this.saldo = 0.0;
  }

  @Override
  public String getNumero() {
    return numero;
  }

  @Override
  public Cliente getCliente() {
    return cliente;
  }

  @Override
  public TipoConta getTipo() {
    return TipoConta.CORRENTE;
  }

  @Override
  public double getSaldo() {
    return saldo;
  }

  @Override
  public void depositar(double valor) {
    if (valor <= 0){
      throw new IllegalArgumentException("Valor inválido");
    }
    saldo += valor;
  }

  @Override
  public void sacar(double valor) {
    if (valor <= 0) {
      throw new IllegalArgumentException("Valor inválido");
    }

    if (valor > saldo){
      throw new SaldoInsuficienteException("Saldo Insuficiente");
    }
    saldo -= valor;
  }

  @Override
  public String toString(){
    return " Titular: " + getCliente() + " Tipo da conta: " + getTipo();
  }
}
