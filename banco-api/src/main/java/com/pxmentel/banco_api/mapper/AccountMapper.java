package com.pxmentel.banco_api.mapper;

import com.pxmentel.banco_api.domain.entity.Account;
import com.pxmentel.banco_api.dto.response.AccountResponse;

public class AccountMapper {

  public static AccountResponse toResponse(Account account){
    return new AccountResponse(
        account.getAccountNumber(),
        account.getUser().getName(),
        account.getBalance()
    );
  }
}
