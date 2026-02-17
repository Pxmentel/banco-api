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
@DiscriminatorValue("CORRENTE")
public class ContaCorrente extends Conta {
  private Double limite;

  @Override
  public void sacar(double valor) {
    if ((getSaldo() + this.limite) < valor) {
      throw new IllegalArgumentException("Saldo e limite insuficientes");
    }
    setSaldo(getSaldo() - valor);
  }
}