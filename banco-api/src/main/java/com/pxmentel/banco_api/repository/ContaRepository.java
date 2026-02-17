package com.pxmentel.banco_api.repository;

import com.pxmentel.banco_api.domain.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

  Optional<Conta> findByNumero(String numero);

  boolean existsByNumero(String numero);

  // 1. Soma o saldo de todas as contas direto no banco
  @Query("SELECT SUM(c.saldo) FROM Conta c")
  Double sumAllSaldos();

  // 2. Busca a conta com o maior saldo (limita a 1 resultado)
  @Query("SELECT c FROM Conta c ORDER BY c.saldo DESC LIMIT 1")
  Optional<Conta> findTopByOrderBySaldoDesc();

  // 3. Filtro de saldo mínimo usando convenção de nome do Spring Data
  List<Conta> findBySaldoGreaterThanEqual(double valorMinimo);
}