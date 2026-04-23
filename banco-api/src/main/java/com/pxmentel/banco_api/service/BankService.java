package com.pxmentel.banco_api.service;

import com.pxmentel.banco_api.domain.entity.*;
import com.pxmentel.banco_api.domain.entity.Account;
import com.pxmentel.banco_api.domain.entity.CheckingAccount;
import com.pxmentel.banco_api.domain.enumm.AccountType;
import com.pxmentel.banco_api.exception.AccountNotFoundException;
import com.pxmentel.banco_api.dto.request.CreateAccountRequest;
import com.pxmentel.banco_api.repository.UserRepository;
import com.pxmentel.banco_api.repository.AccountRepository; // Importar o novo repository
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankService {

  private final UserRepository userRepository;
  private final AccountRepository accountRepository;

  public BankService(UserRepository userRepository, AccountRepository accountRepository) {
    this.userRepository = userRepository;
    this.accountRepository = accountRepository;
  }

  @Transactional
  public Account createAccount(CreateAccountRequest request) {
    if (request == null) throw new IllegalArgumentException("Dados inválidos");

    if (accountRepository.existsByAccountNumber(request.getAccountNumber())) {
      throw new IllegalArgumentException("Conta já existe");
    }

    User user = userRepository.findByCpf(request.getUserDocument())
        .orElseGet(() -> {
          User newUser = new User(request.getUserName(), request.getUserDocument());
          return userRepository.save(newUser);
        });

    Account account;

    if (request.getAccountType() == AccountType.CHECKING) {
      CheckingAccount checkingAccount = new CheckingAccount();
      checkingAccount.setAccountLimit(request.getAccountLimit());
      account = checkingAccount;
    } else if (request.getAccountType() == AccountType.SAVINGS) {
      SavingsAccount savingsAccount = new SavingsAccount();
      savingsAccount.setYieldRate(request.getYieldRate());
      account = savingsAccount;
    } else {
      throw new IllegalArgumentException("Tipo de conta inválido");
    }


    account.setAccountNumber(request.getAccountNumber());
    account.setUser(user);
    account.setBalance(0.0);

    return accountRepository.save(account);
  }

  public Account searchAccount(String accountNumber) {
    return accountRepository.findByAccountNumber(accountNumber)
        .orElseThrow(() -> new AccountNotFoundException("Conta não encontrada: " + accountNumber));
  }

  public List<Account> accountList() {
    return accountRepository.findAll();
  }

  public int numberOfAccounts() {
    return (int) accountRepository.count();
  }

  @Transactional
  public Account deposit (String accountNumber, double value) {
    Account account = searchAccount(accountNumber);
    account.setBalance(account.getBalance() + value);
    return accountRepository.save(account);
  }

  @Transactional
  public Account withdraw(String accountNumber, double value) {
    Account account = searchAccount(accountNumber);
    if (account.getBalance() < value) throw new IllegalArgumentException("Saldo insuficiente");

    account.setBalance(account.getBalance() - value);
    return accountRepository.save(account);
  }

  @Transactional
  public void transfer (String source, String destination, double value) {
    Account sourceAccount = searchAccount(source);
    Account destinationAccount = searchAccount(destination);

    sourceAccount.withdraw(value);
    destinationAccount.deposit(value);
  }

  public double totalBalance() {
    Double total = accountRepository.sumAllBalances();
    return total != null ? total : 0.0;
  }

  public Account biggestBalanceAccount() {
    return accountRepository.findTopByOrderByBalanceDesc()
        .orElseThrow(() -> new IllegalArgumentException("Não existem contas cadastradas"));
  }

  public List<Account> minimumBalanceAccounts(double minimumValue) {
    return accountRepository.findByBalanceGreaterThanEqual(minimumValue);
  }
}