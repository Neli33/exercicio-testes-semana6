package com.example.exerciciotestes.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoRequest {
    private String nomeProduto;
    private Double valorProduto;

}
