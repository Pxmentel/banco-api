package com.pxmentel.banco_api.dto.response;

public class ContaResponse {
  private String numero;
  private String nomeCliente;
  private double saldo;

  public ContaResponse(String numero, String nomeCliente, double saldo) {
    this.numero = numero;
    this.nomeCliente = nomeCliente;
    this.saldo = saldo;
  }

  public String getNumero() {
    return numero;
  }

  public String getNomeCliente() {
    return nomeCliente;
  }

  public double getSaldo() {
    return saldo;
  }
}
