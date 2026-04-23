package com.pxmentel.banco_api.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse {
  private String accountNumber;
  private String userName;
  private double balance;

  public AccountResponse(String accountNumber, String userName, double balance) {
    this.accountNumber = accountNumber;
    this.userName = userName;
    this.balance = balance;
  }
}
