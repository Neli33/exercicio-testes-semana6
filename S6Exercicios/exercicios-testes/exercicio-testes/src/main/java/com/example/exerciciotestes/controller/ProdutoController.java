package com.example.exerciciotestes.controller;

import com.example.exerciciotestes.controller.request.ProdutoRequest;
import com.example.exerciciotestes.model.Produto;
import com.example.exerciciotestes.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class  ProdutoController {

  private final ProdutoService produtoService;
  public ProdutoController(ProdutoService produtoService) {
    this.produtoService = produtoService;
  }

  @GetMapping
  public ResponseEntity<Object> getAllProduto(){

    List<Produto> produtos = this.produtoService.buscaTodosProdutos();

    if(produtos.isEmpty()){
      return ResponseEntity.status(HttpStatus.OK).body("Nenhuma produto");
    }
      return ResponseEntity.status(HttpStatus.OK).body(produtos);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Object> getProdutoById(@PathVariable(value = "id") Long id){

    Produto produto = this.produtoService.buscaProdutoPorId(id);

    if(produto==null){
      return ResponseEntity.status(HttpStatus.OK).body("Nenhuma Produto");
    }
    return ResponseEntity.status(HttpStatus.OK).body(produto);
  }

  @PostMapping
  public ResponseEntity<Produto> saveProduto(@Valid @RequestBody ProdutoRequest produtoRequest){

    Produto produto =this.produtoService.salvarProduto(produtoRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(produto);

  }

  @PutMapping(path = "{id}")
  public ResponseEntity<Object> updateProduto(@PathVariable (value = "id")Long id,
                                               @Valid @RequestBody ProdutoRequest produtoRequest){

    Produto produto =this.produtoService.atualizarProduto(id,produtoRequest);

    return ResponseEntity.status(HttpStatus.OK).body(produto);

  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity<Object> deleteProduto(@PathVariable (value = "id")Long id){
    this.produtoService.detelaProdutoPorId(id);

    return ResponseEntity.status(HttpStatus.OK).body(null);

  }

}
