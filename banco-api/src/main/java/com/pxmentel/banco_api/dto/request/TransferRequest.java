package com.pxmentel.banco_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class TransferRequest {

  @NotBlank
  private String contaOrigem;

  @NotBlank
  private String contaDestino;

  @Positive
  private double valor;

  public String getContaOrigem() {
    return contaOrigem;
  }

  public String getContaDestino() {
    return contaDestino;
  }

  public double getValor() {
    return valor;
  }

  public void setContaDestino(String contaDestino) {
    this.contaDestino = contaDestino;
  }

  public void setContaOrigem(String contaOrigem) {
    this.contaOrigem = contaOrigem;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }
}
