package com.example.exerciciotestes.repository;

import com.example.exerciciotestes.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda,Long> {

}
