package com.pxmentel.banco_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CriarContaRequest {
  @NotBlank(message = "Número da conta é obrigatório")
  private String numero;

  @Size(min = 3, message = "Nome precisa ter no mínimo 3 caracteres")
  @NotBlank(message = "Nome do cliente é obrigatório")
  private String nomeCliente;

  @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos numéricos")
  @NotBlank(message = "Documento do cliente é obrigatório")
  private String documentoCliente;

  public String getNumero() {
    return numero;
  }

  public String getNomeCliente() {
    return nomeCliente;
  }

  public String getDocumentoCliente() {
    return documentoCliente;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public void setNomeCliente(String nomeCliente) {
    this.nomeCliente = nomeCliente;
  }

  public void setDocumentoCliente(String documentoCliente) {
    this.documentoCliente = documentoCliente;
  }
}
