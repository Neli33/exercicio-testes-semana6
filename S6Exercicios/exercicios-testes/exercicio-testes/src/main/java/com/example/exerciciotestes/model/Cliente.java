package com.example.exerciciotestes.model;

import lombok.*;

import javax.persistence.*;

@Entity(name="cliente")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cliente {
  @Id
  @Column(name = "id",  insertable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome_cliente")
  private String nomeCliente;

  @Column(name = "saldo_cliente")
  private Double saldoCliente;

  public Cliente(String nomeCliente, Double saldoCliente) {
    this.nomeCliente = nomeCliente;
    this.saldoCliente = saldoCliente;
  }
}
