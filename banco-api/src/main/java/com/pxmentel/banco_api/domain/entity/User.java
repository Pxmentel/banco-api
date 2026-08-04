package com.pxmentel.banco_api.domain.entity;

import com.pxmentel.banco_api.domain.enumm.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(unique = true)
  private String cpf;

  private String password;

  @Enumerated(EnumType.STRING)
  private UserRole role;

  public User(String name, String cpf) {
    if (name == null || name.isBlank()) throw new IllegalArgumentException("Nome é obrigatório");
    if (cpf == null || cpf.isBlank()) throw new IllegalArgumentException("CPF é obrigatório");
    this.name = name;
    this.cpf = cpf;
  }

}
