package com.pxmentel.banco_api.dto.request;

import com.pxmentel.banco_api.domain.enumm.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {
  @NotBlank(message = "Número da conta é obrigatório")
  private String accountNumber;

  @Size(min = 3, message = "Nome precisa ter no mínimo 3 caracteres")
  @NotBlank(message = "Nome do cliente é obrigatório")
  private String userName;

  @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
  @NotBlank(message = "Documento do cliente é obrigatório")
  private String userDocument;

  @NotNull(message = "Tipo da conta é obrigatório")
  private AccountType accountType;

  private Double accountLimit;
  private Double yieldRate;
}
