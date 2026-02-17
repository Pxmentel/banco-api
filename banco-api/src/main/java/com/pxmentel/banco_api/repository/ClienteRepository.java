package com.pxmentel.banco_api.repository;

import com.pxmentel.banco_api.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  // O Spring gera automaticamente métodos como save(), findAll(), findById()

  // Vamos criar um método customizado para buscar por CPF
  Optional<Cliente> findByCpf(String cpf);
}