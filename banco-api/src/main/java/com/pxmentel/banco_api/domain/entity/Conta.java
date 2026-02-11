package com.pxmentel.banco_api.domain.entity;

import com.pxmentel.banco_api.domain.enumm.TipoConta;

public interface Conta {

  String getNumero();
  Cliente getCliente();
  TipoConta getTipo();
  double getSaldo();

  void depositar(double valor);
  void sacar(double valor);
}
