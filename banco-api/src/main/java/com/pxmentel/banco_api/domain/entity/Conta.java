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
public abstract class Conta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String numero;
  private Double saldo = 0.0;

  @ManyToOne
  @JoinColumn(name = "cliente_id") // Cria a Chave Estrangeira para o Cliente
  private Cliente cliente;

  public abstract void sacar(double valor);

  public void depositar(double valor) {
    if (valor <= 0) {
      throw new IllegalArgumentException("Valor inválido para depósito");
    }
    this.saldo += valor;
  }
}