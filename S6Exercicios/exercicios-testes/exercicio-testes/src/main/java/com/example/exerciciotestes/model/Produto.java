package com.example.exerciciotestes.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "produto")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto", insertable = false)
    private Long id;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "valor_produto")
    private Double valorProduto;

    public Produto(String nomeProduto, Double valorProduto) {
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
    }
}
