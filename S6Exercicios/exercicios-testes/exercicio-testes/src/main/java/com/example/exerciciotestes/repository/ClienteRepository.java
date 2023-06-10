package com.example.exerciciotestes.repository;

import com.example.exerciciotestes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Object buscaClientePorId(Long clienteId);
}
