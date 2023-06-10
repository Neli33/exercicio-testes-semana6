package com.example.exerciciotestes.controller;

import com.example.exerciciotestes.controller.request.ClienteRequest;
import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class  ClienteController {

  private final ClienteService clienteService;
  public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  @GetMapping
  public ResponseEntity<Object> getAllCliente(){

    List<Cliente> clientes = this.clienteService.buscaTodosClientes();
    if(clientes.isEmpty()){
      return ResponseEntity.status(HttpStatus.OK).body("Nenhuma cliente");
    }
      return ResponseEntity.status(HttpStatus.OK).body(clientes);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Object> getClienteById(@PathVariable(value = "id") Long id){

    Cliente cliente = this.clienteService.buscaClientePorId(id);
    if(cliente==null){
      return ResponseEntity.status(HttpStatus.OK).body("Nenhum Cliente");
    }
    return ResponseEntity.status(HttpStatus.OK).body(cliente);
  }

  @PostMapping
  public ResponseEntity<Cliente> saveCliente(@Valid @RequestBody ClienteRequest clienteRequest){
    Cliente clienteSalvo =this.clienteService.salvarCliente(clienteRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
  }

  @PutMapping(path = "{id}")
  public ResponseEntity<Object> updateCliente(@PathVariable (value = "id")Long id,
                                               @Valid @RequestBody ClienteRequest clienteRequest){
    Cliente clienteAtualizado = this.clienteService.atualizarCliente(id,clienteRequest);
    return ResponseEntity.status(HttpStatus.OK).body(clienteAtualizado);
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity<Object> deleteCliente(@PathVariable (value = "id")Long id){
    this.clienteService.detelaClientePorId(id);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

}
