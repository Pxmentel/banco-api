package com.pxmentel.banco_api.domain.entity;

public class Cliente {
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
