package com.example.exerciciotestes.service;

import com.example.exerciciotestes.controller.request.ProdutoRequest;
import com.example.exerciciotestes.model.Produto;
import com.example.exerciciotestes.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository ProdutoRepository) {
        this.produtoRepository = ProdutoRepository;
    }


    public List<Produto> buscaTodosProdutos(){
        return this.produtoRepository.findAll();
    }

    public Produto salvarProduto( ProdutoRequest produto){
        return this.produtoRepository.save(new Produto(produto.getNomeProduto(), produto.getValorProduto()));
    }

    public Produto atualizarProduto(Long id, ProdutoRequest produtoNovo){
        Produto produtoAtual = buscaProdutoPorId(id);
        if(produtoAtual == null){
            return null;
        }
        produtoAtual.setNomeProduto(produtoNovo.getNomeProduto());
        produtoAtual.setValorProduto(produtoNovo.getValorProduto());
        return this.produtoRepository.save(produtoAtual);
    }
    public Produto buscaProdutoPorId (Long id){
        return this.produtoRepository.findById(id).orElse(null);
    }

    public void detelaProdutoPorId(Long id){
        this.produtoRepository.deleteById(id);
    }
}
