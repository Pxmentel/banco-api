package com.pxmentel.banco_api.controller;

import com.pxmentel.banco_api.domain.entity.Account;
import com.pxmentel.banco_api.dto.request.CreateAccountRequest;
import com.pxmentel.banco_api.dto.request.DepositRequest;
import com.pxmentel.banco_api.dto.request.WithdrawRequest;
import com.pxmentel.banco_api.dto.request.TransferRequest;
import com.pxmentel.banco_api.dto.response.AccountResponse;
import com.pxmentel.banco_api.mapper.AccountMapper;
import com.pxmentel.banco_api.service.BankService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
  private final BankService bankService;
  public BankController(BankService bankService){
    this.bankService = bankService;
  }

  @PostMapping("/account")
  public ResponseEntity<AccountResponse> createAccount (@Valid @RequestBody CreateAccountRequest request) {

    Account accountCreated = bankService.createAccount(request);

    AccountResponse response = AccountMapper.toResponse(accountCreated);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(response);
  }

  @GetMapping("/accounts")
  public List<AccountResponse> accountList() {
    return bankService.accountList()
        .stream()
        .map(AccountMapper::toResponse)
        .toList();
  }

  @GetMapping("/accounts/{accountNumber}")
  public ResponseEntity<AccountResponse> searchAccount(@PathVariable String accountNumber) {
    AccountResponse response = AccountMapper.toResponse(
        bankService.searchAccount(accountNumber)
    );
    return ResponseEntity.ok(response);
  }

  @GetMapping("/accounts/value")
  public ResponseEntity<Integer> numberOfAccounts() {
    return ResponseEntity.ok(bankService.numberOfAccounts());
  }

  @GetMapping("/accounts/totalBalance")
  public ResponseEntity<Double> totalBalance() {
    return ResponseEntity.ok(bankService.totalBalance());
  }

  @GetMapping("/accounts/biggestBalance")
  public ResponseEntity<AccountResponse> biggestBalanceAccount() {
    AccountResponse response = AccountMapper.toResponse(
        bankService.biggestBalanceAccount()
    );
    return ResponseEntity.ok(response);
  }

  @GetMapping("/accounts/minimumBalance/{value}")
  public List<AccountResponse> minimumBalanceAccounts(@PathVariable double value) {
    return bankService.minimumBalanceAccounts(value)
        .stream()
        .map(AccountMapper::toResponse)
        .toList();
  }

  @PostMapping("/accounts/{accountNumber}/deposit")
  public ResponseEntity<AccountResponse> deposit(
      @PathVariable String accountNumber,
      @Valid @RequestBody DepositRequest request
      ) {

    Account account = bankService.deposit(accountNumber, request.getValue());

    return ResponseEntity.ok(
        AccountMapper.toResponse(account)
    );
  }

  @PostMapping("/accounts/{accountNumber}/withdraw")
  public ResponseEntity<AccountResponse> withdraw(
      @PathVariable String accountNumber,
      @Valid @RequestBody WithdrawRequest request
  ) {

    Account account = bankService.withdraw(accountNumber, request.getValue());

    return ResponseEntity.ok(
        AccountMapper.toResponse(account)
    );
  }

  @PostMapping("/accounts/transfer")
  public ResponseEntity<Void> transfer(
      @Valid @RequestBody TransferRequest request
  ) {

    bankService.transfer(
        request.getContaOrigem(),
        request.getContaDestino(),
        request.getValor()
    );

    return ResponseEntity.ok().build();
  }

}
