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
public class SavingsAccount extends Account {
  private Double yieldRate;

  @Override
  public void withdraw(double value) {
    if (getBalance() < value) {
      throw new IllegalArgumentException("Saldo insuficiente na poupança");
    }
    setBalance(getBalance() - value);
  }
}