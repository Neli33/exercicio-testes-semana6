package com.example.exerciciotestes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "venda")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Venda {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "valor_venda")
  private Double valorVenda;

  @ManyToOne
  private Cliente cliente;

  @ManyToMany
  private List<Produto> produtos;

  public Venda(Double valorVenda, Cliente cliente, List<Produto> produtos) {
    this.valorVenda = valorVenda;
    this.cliente = cliente;
    this.produtos = produtos;
  }
}
