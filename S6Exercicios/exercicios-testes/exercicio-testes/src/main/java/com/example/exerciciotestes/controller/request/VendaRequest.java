package com.example.exerciciotestes.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class VendaRequest {
  private Long clienteId;
  private List<Long> produtos;
  private Double valorVenda;
}
