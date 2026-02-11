package com.pxmentel.banco_api.mapper;

import com.pxmentel.banco_api.domain.entity.Conta;
import com.pxmentel.banco_api.dto.response.ContaResponse;

public class ContaMapper {

  public static ContaResponse toResponse(Conta conta){
    return new ContaResponse(
        conta.getNumero(),
        conta.getCliente().getNome(),
        conta.getSaldo()
    );
  }
}
