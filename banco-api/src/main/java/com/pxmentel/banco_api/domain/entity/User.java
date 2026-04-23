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
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String cpf;
  private String password;

  public User(String name, String cpf){
    if (name == null || name.isBlank()){
      throw new IllegalArgumentException("Nome é obrigatório");
    }
    if (cpf == null || cpf.isBlank()){
      throw new IllegalArgumentException("CPF é obrigatório");
    }
    this.name = name;
    this.cpf = cpf;
  }

  public String getName() {
    return name;
  }

  public String getCpf() {
    return cpf;
  }

  public String getUserName() {
    return cpf;
  }

  public String getAuthorities() {
    return null;
  }

}
