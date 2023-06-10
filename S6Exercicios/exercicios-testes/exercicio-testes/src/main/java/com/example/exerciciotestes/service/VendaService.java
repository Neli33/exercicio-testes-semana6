package com.example.exerciciotestes.service;

import com.example.exerciciotestes.controller.request.VendaRequest;
import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.model.Produto;
import com.example.exerciciotestes.model.Venda;
import com.example.exerciciotestes.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public VendaService(VendaRepository VendaRepository, ClienteService clienteService, ProdutoService produtoService) {
        this.vendaRepository = VendaRepository;
        this.clienteService = clienteService;
        this.produtoService = produtoService;
    }


    public List<Venda> buscaTodosVendas(){
        return this.vendaRepository.findAll();
    }

    //Exercício 9
    public Venda realizarVenda(VendaRequest vendaRequest){
        Cliente cliente = clienteService.buscaClientePorId(vendaRequest.getClienteId());
        List<Produto> produtos = new ArrayList<>();
        vendaRequest.getProdutos().forEach(
                id-> produtos.add(produtoService.buscaProdutoPorId(id))
        );

        Venda venda =new Venda(vendaRequest.getValorVenda(), cliente, produtos);

        vendaRepository.save(venda);

        return venda;
    }

    public Venda buscaVendaPorId (Long id){
        return this.vendaRepository.findById(id).orElse(null);
    }

}
