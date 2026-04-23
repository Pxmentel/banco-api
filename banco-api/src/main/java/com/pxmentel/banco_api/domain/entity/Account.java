package com.pxmentel.banco_api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "conta") // <--- O Hibernate vai criar essa tabela agora
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING)
public abstract class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String accountNumber;
  private Double balance = 0.0;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private User user;

  public abstract void withdraw(double value);

  public void deposit(double value) {
    if (value <= 0) {
      throw new IllegalArgumentException("Valor inválido para depósito");
    }
    this.balance += value;
  }
}