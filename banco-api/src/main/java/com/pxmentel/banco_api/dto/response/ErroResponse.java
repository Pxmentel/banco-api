package com.pxmentel.banco_api.dto.response;

import java.time.LocalDateTime;

public class ErroResponse {

  private int status;
  private String mensagem;
  private LocalDateTime timestamp;

  public ErroResponse(int status, String mensagem) {
    this.status = status;
    this.mensagem = mensagem;
    this.timestamp = LocalDateTime.now();
  }

  public int getStatus() {
    return status;
  }

  public String getMensagem() {
    return mensagem;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }
}
