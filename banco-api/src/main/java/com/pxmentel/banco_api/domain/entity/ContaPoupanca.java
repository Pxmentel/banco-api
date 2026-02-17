package com.pxmentel.banco_api.domain.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("POUPANCA")
public class ContaPoupanca extends Conta {
  private Double taxaRendimento;

  @Override
  public void sacar(double valor) {
    if (getSaldo() < valor) {
      throw new IllegalArgumentException("Saldo insuficiente na poupança");
    }
    setSaldo(getSaldo() - valor);
  }
}