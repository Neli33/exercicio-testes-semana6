package com.example.exerciciotestes.controller;

import com.example.exerciciotestes.controller.request.VendaRequest;
import com.example.exerciciotestes.model.Venda;
import com.example.exerciciotestes.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

  private final VendaService vendaService;
  public VendaController(VendaService vendaService) {
    this.vendaService = vendaService;
  }

  @GetMapping
  public ResponseEntity<Object> getAllVenda(){

    List<Venda> vendas = this.vendaService.buscaTodosVendas();

    if(vendas.isEmpty()){
      return ResponseEntity.status(HttpStatus.OK).body("Nenhuma venda");
    }
      return ResponseEntity.status(HttpStatus.OK).body(vendas);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Object> getVendaById(@PathVariable(value = "id") Long id){

    Venda venda = this.vendaService.buscaVendaPorId(id);

    if(venda==null){
      return ResponseEntity.status(HttpStatus.OK).body("Nenhuma Venda");
    }
    return ResponseEntity.status(HttpStatus.OK).body(venda);
  }

  @PostMapping
  public ResponseEntity<Venda> saveVenda(@Valid @RequestBody VendaRequest vendaRequest){
    Venda venda = this.vendaService.realizarVenda(vendaRequest);
    return ResponseEntity.status(HttpStatus.OK).body(venda);
  }

}
