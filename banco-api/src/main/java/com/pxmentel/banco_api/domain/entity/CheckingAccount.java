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
@DiscriminatorValue("CHECKING")
public class CheckingAccount extends Account {
  private Double accountLimit;

  @Override
  public void withdraw(double value) {
    if ((getBalance() + this.accountLimit) < value) {
      throw new IllegalArgumentException("Saldo e limite insuficientes");
    }
    setBalance(getBalance() - value);
  }
}