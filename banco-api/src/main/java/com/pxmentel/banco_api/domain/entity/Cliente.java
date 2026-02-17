package com.pxmentel.banco_api.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  private String cpf;
  public Cliente (String nome, String cpf){
    if (nome == null || nome.isBlank()){
      throw new IllegalArgumentException("Nome é obrigatório");
    }
    if (cpf == null || cpf.isBlank()){
      throw new IllegalArgumentException("CPF é obrigatório");
    }
    this.nome = nome;
    this.cpf = cpf;
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }
}
