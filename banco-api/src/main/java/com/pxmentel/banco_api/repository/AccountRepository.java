package com.pxmentel.banco_api.repository;

import com.pxmentel.banco_api.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  Optional<Account> findByAccountNumber(String accountNumber);

  boolean existsByAccountNumber(String accountNumber);

  @Query("SELECT SUM(c.saldo) FROM Account c")
  Double sumAllBalances();

  @Query("SELECT c FROM Account c ORDER BY c.saldo DESC LIMIT 1")
  Optional<Account> findTopByOrderByBalanceDesc();

  List<Account> findByBalanceGreaterThanEqual(double minimumValue);
}