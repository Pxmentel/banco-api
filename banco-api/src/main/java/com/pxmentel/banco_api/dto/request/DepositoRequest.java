package com.pxmentel.banco_api.dto.request;

import jakarta.validation.constraints.Positive;

public class DepositoRequest {

  @Positive(message = "O valor deve ser maior que zero")
  private double valor;

  public double getValor() {
    return valor;
  }
}
