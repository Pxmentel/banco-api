package com.pxmentel.banco_api.dto.request;

import com.pxmentel.banco_api.domain.enumm.TipoConta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriarContaRequest {
  @NotBlank(message = "Número da conta é obrigatório")
  private String numero;

  @Size(min = 3, message = "Nome precisa ter no mínimo 3 caracteres")
  @NotBlank(message = "Nome do cliente é obrigatório")
  private String nomeCliente;

  @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
  @NotBlank(message = "Documento do cliente é obrigatório")
  private String documentoCliente;

  @NotNull(message = "Tipo da conta é obrigatório")
  private TipoConta tipo;

  private Double limite;
  private Double taxaRendimento;
}
