package com.pxmentel.banco_api.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositRequest {

  @Positive(message = "O valor deve ser maior que zero")
  private double value;

}
