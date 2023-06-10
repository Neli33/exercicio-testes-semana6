package com.example.exerciciotestes.service;


import com.example.exerciciotestes.controller.request.ProdutoRequest;
import com.example.exerciciotestes.model.Produto;
import com.example.exerciciotestes.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    ProdutoRepository produtoRepository;

    @InjectMocks
   ProdutoService produtoService;

    //Exrecício 5
    @Test
    void buscaTodosProdutos() {
        List<Produto> produtoMock =List.of(
                new Produto("Caneta", 5.0),
                new Produto("Lapis", 3.00),
                new Produto("Marcador de texto", 10.00)
        );

        when(produtoRepository.findAll()).thenReturn(produtoMock);

        List<Produto> produtosResultado = produtoService.buscaTodosProdutos();

        assertNotNull(Collections.unmodifiableList(produtosResultado));
        assertEquals(produtoMock.get(0).getNomeProduto(), produtosResultado.get(0).getNomeProduto());

        verify(produtoRepository).findAll();
    }

    //Exrecício 6
    @Test
    void salvarProduto() {
        Produto produtoMock =
                new Produto("Livo", 50.0);


        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoMock);


        Produto produtoResultado = produtoService.salvarProduto(
                new ProdutoRequest("Livro", 50.0)
        );

        assertNotNull(produtoResultado);
        assertEquals(produtoMock.getNomeProduto(),produtoResultado.getNomeProduto());
        verify(produtoRepository).save(any());
    }

    //Exrecício 7
    @Test
    void atualizarProduto() {

     Produto produtoMock = new Produto("Livro", 50.0 );
        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoMock);
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produtoMock));

        Produto atualizarProduto =
         produtoRepository.save(new Produto("Livro", 10.0));

        assertNotNull(atualizarProduto);
        assertEquals(produtoMock.getId(), atualizarProduto.getId());
        assertEquals(produtoMock.getNomeProduto(), atualizarProduto.getNomeProduto());
        assertEquals(produtoMock.getValorProduto(), atualizarProduto.getValorProduto());

        verify(produtoRepository).save(any());
        verify(produtoRepository).findById(anyLong());


    }

    //Exrecício 5
    @Test
    void buscaProdutoPorId() {
        Long id = 1L;

        Produto produtoMock = new Produto();
        produtoMock.setId(id);
        produtoMock.setNomeProduto("Caderno");
        produtoMock.setValorProduto(10.0);

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoMock));


        Produto produtoEncontrado = produtoService.buscaProdutoPorId(id);

        assertNotNull(produtoEncontrado);
        assertEquals(id, produtoEncontrado.getId());
        assertEquals(produtoMock.getNomeProduto(), produtoEncontrado.getNomeProduto());
        assertEquals(produtoMock.getValorProduto(), produtoEncontrado.getValorProduto());
    }

    //Exrecício 8
    @Test
    void detelaProdutoPorId() {
        doNothing().when(produtoRepository).deleteById(anyLong());
        produtoService.detelaProdutoPorId(1L);

        verify(produtoRepository).deleteById(anyLong());

    }
}